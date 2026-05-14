package com.reporta.report_service.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.reporta.report_service.services.impl.JwtServiceImpl;

import io.jsonwebtoken.Claims;

import java.io.IOException;
import java.util.Collections;

import com.reporta.report_service.security.JwtUser;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    public JwtAuthFilter() {
        System.out.println("JWT FILTER CREATED");
    }

    @Autowired
    private JwtServiceImpl jwtService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        System.out.println("JWT FILTER RUNNING");

        System.out.println(
    request.getHeader("Authorization")
);

        final String authHeader =
                request.getHeader("Authorization");

        if (authHeader == null ||
                !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        if (jwtService.isTokenValid(token)) {

            Claims claims = jwtService.extractAllClaims(token);

            Integer id = claims.get("id", Integer.class);

            String tipoUser =
                    claims.get("tipoUser", String.class);

            String email = claims.getSubject();

            JwtUser jwtUser = new JwtUser(
                            id,
                            email,
                            tipoUser
                    );

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            jwtUser,
                            null,
                            Collections.emptyList()
                    );

            auth.setDetails(
                    new WebAuthenticationDetailsSource()
                            .buildDetails(request)
            );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}