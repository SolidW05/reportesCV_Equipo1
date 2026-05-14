package com.Ubicacion.Controllers;

import com.Ubicacion.Dtos.GeocodigoInversoR;
import com.Ubicacion.Dtos.ReenvioGeocodigoR;
import com.Ubicacion.Models.Ubicacion;
import com.Ubicacion.Service.GoogleMapsServicio;
import com.Ubicacion.Service.UbicacionService;
import com.netflix.discovery.converters.Auto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/ubicacion")
public class UbicacionControlador {

    @Autowired
    private GoogleMapsServicio googleMapsServicio;

    @Autowired
    private UbicacionService municipioService;

    @PostMapping("/reverse")
    public ResponseEntity<GeocodigoInversoR> reverseGeocode(
            @RequestParam Double lat,
            @RequestParam Double lng) {
        return ResponseEntity.ok(googleMapsServicio.reverseGeocode(lat, lng));
    }

    @GetMapping("/forward")
    public ResponseEntity<ReenvioGeocodigoR> forwardGeocode(
            @RequestParam String query) {
        return ResponseEntity.ok(googleMapsServicio.forwardGeocode(query));
    }

    @GetMapping("/{municipio}")
    public ResponseEntity<List<Long>> obtenerIdPorMunicipio(
            @PathVariable String municipio) {
        return ResponseEntity.ok(municipioService.obtenerIdsPorMunicipio(municipio));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Ubicacion> obtenerDetallesUbicacion(
            @PathVariable Long id) {
        return ResponseEntity.ok(municipioService.obtenerDetallesUbicacionPorId(id));
    }

}
