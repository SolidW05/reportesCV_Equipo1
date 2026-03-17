package com.example.UserService.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@jakarta.persistence.Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String name;

        @Column(unique = true,nullable = false)
        private String email;

        @Column(nullable = false)
        private String password;

        @Enumerated(EnumType.STRING)
        @Column(name = "tipo_usuario", nullable = false)
        @Builder.Default
        private TipoUsuario tipoUser = TipoUsuario.usuario;

        @Column(nullable = false)
        @Builder.Default
        private Boolean activo = false;

        @Column(name = "token_Verificacion")
        private String tokenVerificacion;

    public  enum TipoUsuario{
        usuario, autoridad
    }

        @Override
        public String getUsername() {
                return email; // usamos email como identificador
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                // convertimos tipoUser en un rol que Spring Security entiende
                return List.of(new SimpleGrantedAuthority("ROLE_" + tipoUser.name()));
        }

        @Override
        public boolean isEnabled() {
                return activo; // solo pueden autenticarse usuarios verificados
        }

        // Los siguientes los dejamos en true por ahora
        @Override
        public boolean isAccountNonExpired() { return true; }

        @Override
        public boolean isAccountNonLocked() { return true; }

        @Override
        public boolean isCredentialsNonExpired() { return true; }
}

