package com.proyecto.reportes.security;

import com.proyecto.reportes.models.Usuario;
import com.proyecto.reportes.repositories.UsuarioRepositorio;
import com.proyecto.reportes.services.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioDetailService implements UserDetailsService {

    private String nombre;
    private Integer id;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    // aqui se crea lo que usa spring para poder autenticar a los usuarios
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByEmail(username).orElseThrow(()
                -> new UsernameNotFoundException("Usuario no encontrado"));


        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_"+usuario.getTipoUsuario().name()))
        );
    }
}
