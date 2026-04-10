package com.Ubicacion.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ubicacion.Models.Ubicacion;
import com.Ubicacion.Repository.MunicipioRepositorio;
import com.Ubicacion.Repository.UbicacionRepositorio;

@Service
public class UbicacionService {

    @Autowired
    private MunicipioRepositorio municipioRepositorio;

    @Autowired
    private UbicacionRepositorio ubicacionRepositorio;

    public boolean existeMunicipio(String municipio) {
        return municipioRepositorio.existsByMunicipio(municipio);
    }
    
    public List<Long> obtenerIdsPorMunicipio(String municipio) {
        return ubicacionRepositorio.findIdsByMunicipio(municipio);
    }
    
    public Ubicacion obtenerDetallesUbicacionPorId(Long id) {
        return ubicacionRepositorio.findById(id).orElse(null);
    }
}
