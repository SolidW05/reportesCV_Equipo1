package com.proyecto.autoridades_service.service;

import com.proyecto.autoridades_service.dto.AutoridadRequest;
import com.proyecto.autoridades_service.dto.AutoridadResponse;
import com.proyecto.autoridades_service.model.Autoridad;
import com.proyecto.autoridades_service.model.Municipio;
import com.proyecto.autoridades_service.repository.AutoridadRepository;
import com.proyecto.autoridades_service.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AutoridadService {

    @Autowired
    private AutoridadRepository autoridadRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    // RestTemplate es la herramienta que hace llamadas HTTP a otros servicios
    @Autowired
    private RestTemplate restTemplate;

    // Lee la URL de Ramón desde application.properties
    // El valor después de : es el default si no encuentra la propiedad
    @Value("${user-service.url:http://localhost:8081}")
    private String userServiceUrl;

    public Autoridad crearAutoridad(AutoridadRequest request) {

        // ── lo que ya tenías ──────────────────────────────────────
        Autoridad autoridad = new Autoridad();
        autoridad.setIdUsuario(request.getIdUsuario());
        autoridad.setTelefono(request.getTelefono());
        autoridad.setMunicipioId(request.getMunicipio());
        Autoridad guardada = autoridadRepository.save(autoridad);
        // ──────────────────────────────────────────────────────────

        // ── NUEVO: avisar a Ramón ─────────────────────────────────
        try {
            String url = userServiceUrl + "/api/users/" + request.getIdUsuario() + "/tipo";

            // Body que le mandamos: { "tipo_usuario": "AUTORIDAD" }
            Map<String, String> body = new HashMap<>();
            body.put("tipo_usuario", "AUTORIDAD");

            restTemplate.patchForObject(url, body, String.class);
            System.out.println("User-Service notificado correctamente");

        } catch (Exception e) {
            // Si Ramón falla, tu servicio NO falla
            // La autoridad ya quedó guardada, eso es lo importante
            System.out.println("No se pudo notificar a User-Service: " + e.getMessage());
        }
        // ──────────────────────────────────────────────────────────

        return guardada;
    }

    // ── estos dos métodos se quedan exactamente igual ─────────────

    public Autoridad actualizarAutoridad(Integer id, AutoridadRequest request) {
        Autoridad autoridad = autoridadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autoridad no encontrada"));
        autoridad.setTelefono(request.getTelefono());
        autoridad.setMunicipioId(request.getMunicipio());
        return autoridadRepository.save(autoridad);
    }

    public AutoridadResponse obtenerPorIdUsuario(Integer idUsuario) {
        Autoridad autoridad = autoridadRepository.findByIdUsuario(idUsuario)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Autoridad no encontrada"));

        String nombreMunicipio = municipioRepository
                .findById(autoridad.getMunicipioId())
                .map(Municipio::getMunicipio)
                .orElse("Municipio no encontrado");

        AutoridadResponse response = new AutoridadResponse();
        response.setIdAutoridad(autoridad.getIdAutoridad());
        response.setIdUsuario(autoridad.getIdUsuario());
        response.setTelefono(autoridad.getTelefono());
        response.setMunicipioId(autoridad.getMunicipioId());
        response.setMunicipio(nombreMunicipio);
        return response;
    }
}