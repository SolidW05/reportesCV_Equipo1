package com.proyecto.autoridades_service.service;

import com.proyecto.autoridades_service.dto.AutoridadRequest;
import com.proyecto.autoridades_service.dto.AutoridadResponse;
import com.proyecto.autoridades_service.model.Autoridad;
import com.proyecto.autoridades_service.model.Municipio;
import com.proyecto.autoridades_service.repository.AutoridadRepository;
import com.proyecto.autoridades_service.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoridadService {

    @Autowired
    private AutoridadRepository autoridadRepository;
    @Autowired
    private MunicipioRepository municipioRepository;

    public Autoridad crearAutoridad(AutoridadRequest request) {
        Autoridad autoridad = new Autoridad();
        autoridad.setIdUsuario(request.getIdUsuario());
        autoridad.setTelefono(request.getTelefono());
        autoridad.setMunicipioId(request.getMunicipio());
        return autoridadRepository.save(autoridad);
    }

    public Autoridad actualizarAutoridad(Integer id, AutoridadRequest request) {
        Autoridad autoridad = autoridadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autoridad no encontrada"));
        autoridad.setTelefono(request.getTelefono());
        autoridad.setMunicipioId(request.getMunicipio());

        return autoridadRepository.save(autoridad);
    }


    public AutoridadResponse obtenerPorIdUsuario(Integer idUsuario) {

        // Paso 1: lo mismo que antes, busca la autoridad
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