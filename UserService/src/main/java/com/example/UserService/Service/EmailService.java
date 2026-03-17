package com.example.UserService.Service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    public void verificacionEmail(String email, String tokenVerificacion){
        String subject= "Verifica tu cuenta";
        String path = "/api/Users/verify";
        String message = "Haz clic en el botón para verificar tu correo electrónico";
        sendEmail(email,tokenVerificacion,subject,path,message);
    }
    public void sendEmail(String email, String token,String subject, String path, String message){
        try{
            //La ip se tiene que estar cambiando para que funcione
            String actionUrl = "http://192.168.1.86:8080" + path + "?token=" + token;
            String content = """
        <div style="font-family: Arial, sans-serif; max-width: 600px; margin: auto; padding: 20px; border-radius: 8px; background-color: #f9f9f9; text-align: center;">
            <h2 style="color: #333;">%s</h2>
            <p style="font-size: 16px; color: #555;">%s</p>
            <a href="%s" style="display: inline-block; margin: 20px 0; padding: 10px 20px; font-size: 16px; color: #fff; background-color: #007bff; text-decoration: none; border-radius: 5px;">Verificar cuenta</a>
            <p style="font-size: 14px; color: #777;">O copia y pega este enlace en tu navegador:</p>
            <p style="font-size: 14px; color: #007bff;">%s</p>
            <p style="font-size: 12px; color: #aaa;">Este es un mensaje automático. Por favor no respondas a este correo.</p>
        </div>
    """.formatted(subject, message, actionUrl, actionUrl);
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper= new MimeMessageHelper(mimeMessage, true);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setFrom(from);
            helper.setText(content,true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }

}
