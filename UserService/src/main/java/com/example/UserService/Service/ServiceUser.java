package com.example.UserService.Service;

import com.example.UserService.DTO.AuthUserDTO;
import com.example.UserService.DTO.RegisterUserDTO;
import com.example.UserService.Entity.User;
import com.example.UserService.Exception.EmailYaRegistradoException;
import com.example.UserService.Exception.UsuarioNoEncontradoException;
import com.example.UserService.Repository.RepositoryUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class ServiceUser implements UserDetailsService {
    private final RepositoryUser respositoryUser;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return respositoryUser.findByEmail(email)
                .orElseThrow(() -> new UsuarioNoEncontradoException(email));
    }

    public AuthUserDTO registerUser(RegisterUserDTO regdto){
        if (respositoryUser.findByEmail(regdto.getEmail()).isPresent()){
            throw new EmailYaRegistradoException(regdto.getEmail());
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
