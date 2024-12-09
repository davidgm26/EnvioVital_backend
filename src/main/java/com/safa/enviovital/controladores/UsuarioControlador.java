package com.safa.enviovital.controladores;

import com.safa.enviovital.dto.UsuarioRequestDTO;
import com.safa.enviovital.dto.UsuarioResponseDTO;
import com.safa.enviovital.excepciones.NotFoundException.UsernameAlredyExistsException;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.modelos.Usuario;
import com.safa.enviovital.servicios.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioControlador {

    private final UsuarioService usuarioService;

    @Operation(
            summary = "Listar todos los usuarios",
            description = "Devuelve una lista de todos los usuarios registrados en el sistema.",
            tags = {"Usuarios"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de usuarios obtenida con éxito.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UsuarioResponseDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor.",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/lista")
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @Operation(
            summary = "Obtener un usuario por ID",
            description = """
        Devuelve la información de un usuario específico basado en su ID.
        Si el usuario no existe, devuelve un error 404.
    """,
            tags = {"Usuarios"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Usuario obtenido con éxito.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Usuario.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuario no encontrado.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor.",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Integer id) {
        Usuario usuario = usuarioService.getUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @Operation(
            summary = "Registrar un usuario administrador",
            description = """
        Crea un nuevo usuario administrador en el sistema.
        Requiere un cuerpo de solicitud con la información del usuario.
        Devuelve el usuario creado.
    """,
            tags = {"Usuarios"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Usuario administrador registrado con éxito.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Usuario.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos de la solicitud inválidos.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor.",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @PostMapping("/registrar/admin")
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody UsuarioRequestDTO requestDTO) throws UsernameAlredyExistsException {
        return ResponseEntity.ok(usuarioService.crearUsuarioAdmin(requestDTO));
    }

    @Operation(
            summary = "Eliminar un usuario",
            description = """
        Elimina un usuario específico basado en su ID.
        Devuelve el resultado de la operación, incluyendo un mensaje y el código de estado.
    """,
            tags = {"Usuarios"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Usuario eliminado con éxito.",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuario no encontrado.",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor.",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response> eliminarUsuario(@PathVariable Integer id) {
        Response respuesta = usuarioService.eliminar(id);
        return ResponseEntity.status(respuesta.getStatusCode()).body(respuesta);
    }
}
