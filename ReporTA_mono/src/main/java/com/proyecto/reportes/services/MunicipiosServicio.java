package com.proyecto.reportes.services;


import com.proyecto.reportes.models.Municipio;
import com.proyecto.reportes.repositories.MunicipioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MunicipiosServicio {
    @Autowired
    private MunicipioRepositorio municipioRepositorio;

    public List<Municipio> obtenerMunicipios(){
        return municipioRepositorio.findAll();
    }

    public Optional<Municipio> obtenerMunicipio(Integer id){
        return municipioRepositorio.findById(id);
    }

}
