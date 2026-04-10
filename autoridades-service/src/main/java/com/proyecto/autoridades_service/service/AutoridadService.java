package com.proyecto.autoridades_service.service;

import com.proyecto.autoridades_service.dto.AutoridadRequest;
import com.proyecto.autoridades_service.model.Autoridad;
import com.proyecto.autoridades_service.repository.AutoridadRepository;
import com.proyecto.autoridades_service.repository.MunicipioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoridadService {

    @Autowired
    private AutoridadRepository autoridadRepository;

    @Autowired
    private MunicipioService municipioService;

    public Autoridad crearAutoridad(AutoridadRequest request) {
        Autoridad autoridad = new Autoridad();
        autoridad.setIdUsuario(request.getIdUsuario());
        autoridad.setTelefono(request.getTelefono());
        if (!municipioService.existeMunicipio(request.getMunicipio())) {
            throw new RuntimeException("Municipio no encontrado");
        }
        autoridad.setMunicipioId(request.getMunicipio());
        return autoridadRepository.save(autoridad);
    }

    public Autoridad actualizarAutoridad(Integer id, AutoridadRequest request) {
        Autoridad autoridad = autoridadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Autoridad no encontrada"));
        autoridad.setTelefono(request.getTelefono());

        if (!municipioService.existeMunicipio(request.getMunicipio())) {
            throw new RuntimeException("Municipio no encontrado");
        }
        autoridad.setMunicipioId(request.getMunicipio());

        return autoridadRepository.save(autoridad);
    }


    public Autoridad obtenerPorIdUsuario(Integer idUsuario) {
        return autoridadRepository.findByIdUsuario(idUsuario)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Autoridad no encontrada"));
    }
}