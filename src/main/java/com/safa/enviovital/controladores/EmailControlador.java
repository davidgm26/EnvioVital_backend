package com.safa.enviovital.controladores;
import com.safa.enviovital.dto.EmailRequestDTO;
import com.safa.enviovital.servicios.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "*") // Permite llamadas desde Angular
public class EmailControlador {

    @Autowired
    private EmailService emailService;

    @Operation(summary = "Envía un correo electrónico de registro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Correo enviado con éxito",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "500", description = "Error al enviar el correo",
                    content = @Content)
    })
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