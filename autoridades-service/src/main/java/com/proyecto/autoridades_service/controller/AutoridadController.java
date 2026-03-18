package com.proyecto.autoridades_service.controller;

import com.proyecto.autoridades_service.dto.AutoridadRequest;
import com.proyecto.autoridades_service.model.Autoridad;
import com.proyecto.autoridades_service.service.AutoridadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autoridad")
public class AutoridadController {

    @Autowired
    private AutoridadService autoridadService;

    @PostMapping
    public ResponseEntity<Autoridad> crearAutoridad(@Valid @RequestBody AutoridadRequest request) {
        Autoridad nueva = autoridadService.crearAutoridad(request);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autoridad> actualizarAutoridad(
            @PathVariable Integer id,
            @Valid @RequestBody AutoridadRequest request) {
        Autoridad actualizada = autoridadService.actualizarAutoridad(id, request);
        return ResponseEntity.ok(actualizada);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Autoridades Service funcionando en puerto 8083!");
    }
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Autoridad> obtenerPorIdUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(autoridadService.obtenerPorIdUsuario(idUsuario));
    }
}