package com.example.UserService.Security;

import com.example.UserService.Service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        // 1. Leer el header Authorization
        final String authHeader = request.getHeader("Authorization");

        // 2. Si no tiene token o no empieza con "Bearer ", dejamos pasar
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // 3. Extraer solo el token (quitamos "Bearer " que son 7 caracteres)
        final String token = authHeader.substring(7);

        // 4. Extraer el email que está dentro del token
        final String email = jwtService.extractUsername(token);

        // 5. Si hay email y no hay sesión activa ya
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // 6. Buscar el usuario en la base de datos
            UserDetails user = userDetailsService.loadUserByUsername(email);

            // 7. Validar que el token sea válido para ese usuario
            if (jwtService.isTokenValid(token, user)) {

                // 8. Registrar al usuario como autenticado en Spring Security
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                user, null, user.getAuthorities()
                        );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 9. Continuar con el siguiente filtro
        chain.doFilter(request, response);
    }
}