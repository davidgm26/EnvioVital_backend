package com.safa.enviovital.controladores;

import com.safa.enviovital.dto.UsuarioRequestDTO;
import com.safa.enviovital.dto.UsuarioResponseDTO;
import com.safa.enviovital.excepciones.NotFoundException.UsernameAlredyExistsException;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.modelos.Usuario;
import com.safa.enviovital.servicios.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioControlador {

    private final UsuarioService usuarioService;

    @Operation(summary = "Obtén la lista de todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se han obtenido la lista de los usuarios correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la lista de usuarios", content = @Content)
    })
    @GetMapping("/lista")
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @Operation(summary = "Obtén un usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha obtenido el usuario correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el usuario", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Integer id) {
        Usuario usuario = usuarioService.getUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @Operation(summary = "Guarda un nuevo usuario administrador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha guardado el usuario correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el usuario", content = @Content)
    })
    @PostMapping("/registrar/admin")
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody UsuarioRequestDTO requestDTO) throws UsernameAlredyExistsException {
        return ResponseEntity.ok(usuarioService.crearUsuarioAdmin(requestDTO));
    }

    @Operation(summary = "Elimina un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Se ha eliminado el usuario correctamente", content = @Content),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el usuario", content = @Content)
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response> eliminarUsuario(@PathVariable Integer id) {
        Response respuesta = usuarioService.eliminar(id);
        return ResponseEntity.status(respuesta.getStatusCode()).body(respuesta);
    }
}