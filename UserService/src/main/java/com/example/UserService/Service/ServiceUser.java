package com.example.UserService.Service;

import com.example.UserService.DTO.AuthUserDTO;
import com.example.UserService.DTO.RegisterUserDTO;
import com.example.UserService.Entity.User;
import com.example.UserService.Repository.RepositoryUser;
import com.example.UserService.Settings.SecurityPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class ServiceUser {
    private final RepositoryUser respositoryUser;
    private final BCryptPasswordEncoder passwordEncoder;
    //private final JwtService jwtService;
    private final EmailService emailService;

    public AuthUserDTO registerUser(RegisterUserDTO regdto){
        if (respositoryUser.findByEmail(regdto.getEmail()).isPresent()){
            throw new RuntimeException("Error");
        }
        String token = UUID.randomUUID().toString();
        User user= User.builder()
                .name(regdto.getName())
                .email(regdto.getEmail())
                .password(passwordEncoder.encode(regdto.getPassword()))
                .activo(false)
                .tipoUser(User.TipoUsuario.usuario)
                .tokenVerificacion(token)
                .build();
        respositoryUser.save(user);
        emailService.verificacionEmail(user.getEmail(), token);
        return new AuthUserDTO("Usuario registrado. Verifica tu correo");
    }

    public boolean verificacionCuenta(String token){
        Optional <User> optional = respositoryUser.findByTokenVerificacion(token);
        if(optional.isPresent()){
            User usuario = optional.get();
            usuario.setActivo(true);
            usuario.setTokenVerificacion(null);
            respositoryUser.save(usuario);
            return true;
        }return false;
    }
}
