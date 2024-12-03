package com.safa.enviovital.controladores;

import com.safa.enviovital.dto.VehiculoRequestDTO;
import com.safa.enviovital.dto.VehiculoResponseDTO;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.modelos.Vehiculo;
import com.safa.enviovital.servicios.VehiculoService;
import lombok.AllArgsConstructor;
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

import java.util.List;

@RestController
@RequestMapping("/vehiculos")
@AllArgsConstructor
public class VehiculoControlador {

    @Autowired
    private final VehiculoService vehiculoService;

    @Operation(summary = "Obtén la lista de todos los vehículos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se han obtenido la lista de los vehículos correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = VehiculoResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la lista de vehículos", content = @Content)
    })
    @GetMapping("/lista")
    public ResponseEntity<List<VehiculoResponseDTO>> listarVehiculos() {
        return ResponseEntity.ok(vehiculoService.getAll());
    }

    @Operation(summary = "Obtén un vehículo por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha obtenido el vehículo correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = VehiculoResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el vehículo", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<VehiculoResponseDTO> obtenerVehiculoPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(vehiculoService.getVehiculoPorId(id));
    }

    @Operation(summary = "Guarda un nuevo vehículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha guardado el vehículo correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = VehiculoResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el vehículo", content = @Content)
    })
    @PostMapping("/guardar")
    public ResponseEntity<VehiculoResponseDTO> guardarVehiculo(@RequestBody VehiculoRequestDTO requestDTO) {
        return ResponseEntity.ok(vehiculoService.guardar(requestDTO));
    }

    @Operation(summary = "Edita un vehículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha editado el vehículo correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = VehiculoResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el vehículo", content = @Content)
    })
    @PutMapping("/editar/{id}")
    public ResponseEntity<VehiculoResponseDTO> editarVehiculo(@PathVariable Integer id, @RequestBody VehiculoRequestDTO requestDTO) {
        return ResponseEntity.ok(vehiculoService.editar(id, requestDTO));
    }

    @Operation(summary = "Elimina un vehículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Se ha eliminado el vehículo correctamente", content = @Content),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el vehículo", content = @Content)
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response> eliminarVehiculo(@PathVariable Integer id) {
        Response respuesta = vehiculoService.eliminar(id);
        return ResponseEntity.status(respuesta.getStatusCode()).body(respuesta);
    }
}