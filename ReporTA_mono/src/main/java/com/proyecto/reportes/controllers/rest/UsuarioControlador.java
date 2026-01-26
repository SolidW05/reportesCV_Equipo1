package com.proyecto.reportes.controllers.rest;

import com.proyecto.reportes.services.AutoridadServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.proyecto.reportes.models.DTO.UsuarioRegistroDTO;
import  com.proyecto.reportes.services.UsuarioServicio;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    @Autowired
    private AutoridadServicio autoridadServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody UsuarioRegistroDTO dto) {
        try {
            usuarioServicio.registrarUsuario(dto);
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Revisa tu correo para validar tu cuenta");
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Hubo un error al enviar el correo de verificación");
            return ResponseEntity.status(500).body(error);
        }
    }

    @GetMapping("/verificar")
    public String verificar(@RequestParam("token") String token, Model model) {
        boolean validado = usuarioServicio.verificarCuenta(token);

        if (validado) {
            return "login";
        } else {
            return "error";
        }
    }


}
