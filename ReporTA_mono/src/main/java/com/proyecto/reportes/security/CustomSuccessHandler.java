package com.proyecto.reportes.security;

import com.proyecto.reportes.models.DTO.UsuarioSesionDTO;
import com.proyecto.reportes.models.Usuario;
import com.proyecto.reportes.repositories.UsuarioRepositorio;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // Obtener los roles del usuario
        Collection<? extends SimpleGrantedAuthority> authorities =
                (Collection<? extends SimpleGrantedAuthority>) authentication.getAuthorities();
        String redirectUrl = "/";

        // Obtener nombre y ID
        HttpSession session = request.getSession();
        String email = authentication.getName();
        Usuario usuario = usuarioRepositorio.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException("Usuario no encontrado"));

        UsuarioSesionDTO dto = new UsuarioSesionDTO(usuario.getNombre(),
                usuario.getIdUsuario(), usuario.getTipoUsuario());
        session.setAttribute("usuarioSesion", dto);

        // esto se tiene que modificar para redireccionar a los respectivos url
        for (GrantedAuthority auth : authorities) {
            String rol = auth.getAuthority();

            if (rol.equals("ROLE_autoridad") && usuario.isActivo()) {
                redirectUrl = "/home";
                break;
            } else if (rol.equals("ROLE_usuario")  && usuario.isActivo()) {
                redirectUrl = "/home";
                break;
            }
        }

        response.sendRedirect(redirectUrl);
    }

}
