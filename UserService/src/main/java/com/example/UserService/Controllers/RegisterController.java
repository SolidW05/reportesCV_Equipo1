package com.example.UserService.Controllers;

import com.example.UserService.DTO.AuthUserDTO;
import com.example.UserService.DTO.RegisterUserDTO;
import com.example.UserService.Entity.User;
import com.example.UserService.Exception.TokenInvalidoException;
import com.example.UserService.Service.ServiceUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Users")
@RequiredArgsConstructor

public class RegisterController {
    private final ServiceUser serviceUser;

    @PostMapping
    public AuthUserDTO registro(@RequestBody @Valid RegisterUserDTO regdto){
        return serviceUser.registerUser(regdto);
    }

    @GetMapping("/verify")
    public String verificar(@RequestParam String token) {
        boolean resultado = serviceUser.verificacionCuenta(token);
        if (!resultado) {
            throw new TokenInvalidoException();
        }
        return "Cuenta verificada correctamente";

    }
}


