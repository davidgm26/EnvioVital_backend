package com.safa.enviovital.controladores;

import com.safa.enviovital.dto.ProvinciaDTO;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.modelos.Provincia;
import com.safa.enviovital.servicios.ProvinciaService;
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
@RequestMapping("/provincias")
@AllArgsConstructor
public class ProvinciaControlador {

    private final ProvinciaService provinciaService;

    @Operation(summary = "Obtén la lista de todas las provincias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se han obtenido la lista de las provincias correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProvinciaDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la lista de provincias", content = @Content)
    })
    @GetMapping("/lista")
    public ResponseEntity<List<ProvinciaDTO>> listarProvincias() {
        return ResponseEntity.ok(provinciaService.getAll());
    }

    @Operation(summary = "Obtén una provincia por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha obtenido la provincia correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProvinciaDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la provincia", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProvinciaDTO> obtenerProvinciaPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(provinciaService.getProvinciaPorId(id));
    }

    @Operation(summary = "Guarda una nueva provincia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha guardado la provincia correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProvinciaDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la provincia", content = @Content)
    })
    @PostMapping("/guardar")
    public ResponseEntity<ProvinciaDTO> guardarProvincia(@RequestBody ProvinciaDTO requestDTO) {
        return ResponseEntity.ok(provinciaService.guardar(requestDTO));
    }

    @Operation(summary = "Edita una provincia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha editado la provincia correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProvinciaDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la provincia", content = @Content)
    })
    @PutMapping("/editar/{id}")
    public ResponseEntity<ProvinciaDTO> editarProvincia(@PathVariable Integer id, @RequestBody ProvinciaDTO requestDTO) {
        return ResponseEntity.ok(provinciaService.actualizar(id, requestDTO));
    }

    @Operation(summary = "Elimina una provincia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Se ha eliminado la provincia correctamente", content = @Content),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la provincia", content = @Content)
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response> eliminarProvincia(@PathVariable Integer id) {
        Response respuesta = provinciaService.eliminar(id);
        return ResponseEntity.status(respuesta.getStatusCode()).body(respuesta);
    }
}