package com.proyecto.reportes.controllers.rest;

import com.proyecto.reportes.models.Municipio;
import com.proyecto.reportes.services.MunicipiosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/municipio")
public class MunicipioControlador {

    @Autowired
    private MunicipiosServicio municipiosServicio;

    @GetMapping
    public List<Municipio> obtenerMunicipios(){

        return municipiosServicio.obtenerMunicipios();
    }

    @GetMapping("/{id}")
    public Optional<Municipio> obtenerMunicipio(@PathVariable Integer id){
        return municipiosServicio.obtenerMunicipio(id);
    }

}
