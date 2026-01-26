package com.proyecto.reportes.services;

import com.proyecto.reportes.models.DTO.UsuarioRegistroDTO;
import com.proyecto.reportes.models.Usuario;
import com.proyecto.reportes.repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.util.*;


@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

   @Autowired
    private JavaMailSender mailSender;

   public void registrarUsuario(UsuarioRegistroDTO dto){
       Usuario usuario = new Usuario();
       usuario.setNombre(dto.getNombre());
       usuario.setEmail(dto.getEmail());
       usuario.setPassword(dto.getPassword());
       usuario.setTokenVerificacion(UUID.randomUUID().toString());
       //   usuario.setCurp(dto.getCurp());


       usuarioRepositorio.save(usuario);
       enviarCorreoVerificacion(usuario);
   }
   public void enviarCorreoVerificacion(Usuario usuario) {
       String enlace = "http://localhost:7512/api/usuarios/verificar?token=" + usuario.getTokenVerificacion();
       String mensaje = "<h3> Bienvenido, " + usuario.getNombre() + "</h3>" +
               "<p>Por favor Ingresa tu correo haciendo clic en el siguiente enlace:</p>" +
               "<a href='" + enlace + "'>Verificar cuenta</a>";
       try {
           MimeMessage message = mailSender.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(message, true);
           helper.setTo(usuario.getEmail());
           helper.setSubject("Verifica tu cuenta");
           helper.setText(mensaje,true);
           mailSender.send(message);

       } catch (MessagingException e) {
           e.printStackTrace();
       }
   }
   public  boolean verificarCuenta(String token){
       Optional<Usuario> optional = usuarioRepositorio.findByTokenVerificacion(token);
       if(optional.isPresent()){
           Usuario usuario = optional.get();
           usuario.setActivo(true);
           usuario.setTokenVerificacion(null);
           usuarioRepositorio.save(usuario);
           return true;
       } return false;
   }
}
