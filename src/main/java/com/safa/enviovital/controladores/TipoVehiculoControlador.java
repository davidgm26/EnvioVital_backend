package com.safa.enviovital.controladores;

import com.safa.enviovital.dto.TipoVehiculoRequestDTO;
import com.safa.enviovital.dto.TipoVehiculoResponseDTO;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.servicios.TipoVehiculoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/tiposVehiculo")
@AllArgsConstructor
public class TipoVehiculoControlador {

    private final TipoVehiculoService tipoVehiculoService;

    @Operation(summary = "Obtén la lista de todos los tipos de vehículos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se han obtenido la lista de los tipos de vehículos correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoVehiculoResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la lista de tipos de vehículos", content = @Content)
    })
    @GetMapping("/lista")
    public ResponseEntity<List<TipoVehiculoResponseDTO>> listarTiposVehiculo() {
        return ResponseEntity.ok(tipoVehiculoService.getAll());
    }

    @Operation(summary = "Obtén un tipo de vehículo por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha obtenido el tipo de vehículo correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoVehiculoResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el tipo de vehículo", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerTipoVehiculoPorId(@PathVariable Integer id) {
        try {
            TipoVehiculoResponseDTO tipoVehiculo = tipoVehiculoService.getTipoVehiculoPorId(id);
            return ResponseEntity.ok(tipoVehiculo);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el Tipo de Vehículo para el ID: " + id);
        }
    }

    @Operation(summary = "Guarda un nuevo tipo de vehículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha guardado el tipo de vehículo correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoVehiculoResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el tipo de vehículo", content = @Content)
    })
    @PostMapping("/guardar")
    public ResponseEntity<TipoVehiculoResponseDTO> guardarTipoVehiculo(@RequestBody TipoVehiculoRequestDTO requestDTO) {
        return ResponseEntity.ok(tipoVehiculoService.guardar(requestDTO));
    }

    @Operation(summary = "Edita un tipo de vehículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha editado el tipo de vehículo correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TipoVehiculoResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el tipo de vehículo", content = @Content)
    })
    @PutMapping("/editar/{id}")
    public ResponseEntity<TipoVehiculoResponseDTO> editarTipoVehiculo(@PathVariable Integer id, @RequestBody TipoVehiculoRequestDTO requestDTO) {
        return ResponseEntity.ok(tipoVehiculoService.editar(id, requestDTO));
    }

    @Operation(summary = "Elimina un tipo de vehículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Se ha eliminado el tipo de vehículo correctamente", content = @Content),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el tipo de vehículo", content = @Content)
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response> eliminarTipoVehiculo(@PathVariable Integer id) {
        Response respuesta = tipoVehiculoService.eliminar(id);
        return ResponseEntity.status(respuesta.getStatusCode()).body(respuesta);
    }
}