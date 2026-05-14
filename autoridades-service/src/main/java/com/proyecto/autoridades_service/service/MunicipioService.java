package com.proyecto.autoridades_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.discovery.converters.Auto;
import com.proyecto.autoridades_service.repository.MunicipioRepository;

@Service
public class MunicipioService {
    
    @Autowired
    private MunicipioRepository municipioRepository;


    public boolean existeMunicipio(Integer id) {
        return municipioRepository.existsById(id);
    }

    public String obtenerMunicipioPorId(Integer id) {
        return municipioRepository.findById(id)
                .map(m -> m.getMunicipio())
                .orElse("Municipio desconocido");
    }

    
}
