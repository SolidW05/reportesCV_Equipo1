package com.example.UserService.Controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/protected")
public class TestController {

    @GetMapping("/perfil")
    public String perfil(@AuthenticationPrincipal UserDetails user) {
        return "Hola " + user.getUsername() + " — acceso autorizado";
    }
}