package com.safa.enviovital.servicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationEmail(String toEmail, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("pablopadillaarroyo@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Registro exitoso");
        message.setText("¡Hola " + name + "!\n\nGracias por registrarte. Tu registro ha sido exitoso.\n\nSaludos.");
        mailSender.send(message);
    }
}
