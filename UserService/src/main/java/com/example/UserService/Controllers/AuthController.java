package com.example.UserService.Controllers;

import com.example.UserService.DTO.AuthUserDTO;
import com.example.UserService.Entity.User;
import com.example.UserService.DTO.LoginUserDTO;
import com.example.UserService.Exception.CredencialesInvalidasException;
import com.example.UserService.Exception.CuentaNoVerificadaException;
import com.example.UserService.Exception.UsuarioNoEncontradoException;
import com.example.UserService.Repository.RepositoryUser;
import com.example.UserService.Service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RepositoryUser repositoryUser;

    @PostMapping("/login")
    public AuthUserDTO login(@RequestBody LoginUserDTO dto) {
       try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
           );
       }catch (Exception e){
           throw new CredencialesInvalidasException();
       }
       User user = repositoryUser.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UsuarioNoEncontradoException(dto.getEmail()));
       if(!user.isEnabled()){
           throw new CuentaNoVerificadaException();
       }
        String token = jwtService.generateToken(user);
        return new AuthUserDTO(token);
    }
}