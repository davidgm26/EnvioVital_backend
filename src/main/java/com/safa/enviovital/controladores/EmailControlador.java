package com.safa.enviovital.controladores;
import com.safa.enviovital.dto.EmailRequestDTO;
import com.safa.enviovital.servicios.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "*") // Permite llamadas desde Angular
public class EmailControlador {

    @Autowired
    private EmailService emailService;

    @Operation(
            summary = "Enviar un correo de registro",
            description = """
            Este endpoint permite enviar un correo electrónico de registro a un usuario específico.
            Requiere un cuerpo con el correo electrónico y el nombre del destinatario. 
            Devuelve un mensaje de éxito o un error en caso de que el envío falle.
        """,
            tags = {"Emails", "Registro"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Correo enviado con éxito.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(example = "Correo enviado con éxito.")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Solicitud mal formada o datos inválidos.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor al enviar el correo.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(example = "Error al enviar el correo.")
                            )
                    )
            }
    )

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
