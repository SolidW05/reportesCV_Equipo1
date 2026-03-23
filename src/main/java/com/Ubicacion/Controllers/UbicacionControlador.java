package com.Ubicacion.Controllers;

import com.Ubicacion.Dtos.GeocodigoInversoR;
import com.Ubicacion.Dtos.ReenvioGeocodigoR;
import com.Ubicacion.Service.GoogleMapsServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ubicacion")
public class UbicacionControlador {

    @Autowired
    private GoogleMapsServicio googleMapsServicio;


    @GetMapping("/reverse")
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
}
