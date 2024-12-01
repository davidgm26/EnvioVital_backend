package com.safa.enviovital.controladores;
import com.safa.enviovital.dto.EmailRequestDTO;
import com.safa.enviovital.servicios.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "*") // Permite llamadas desde Angular
public class EmailControlador {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDTO emailRequestDTO) {
        try {
            emailService.sendRegistrationEmail(emailRequestDTO.getEmail(), emailRequestDTO.getName());
            return ResponseEntity.ok("Correo enviado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al enviar el correo.");
        }
    }
}
