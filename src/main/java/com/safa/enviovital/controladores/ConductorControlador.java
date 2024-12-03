package com.safa.enviovital.controladores;

import com.safa.enviovital.dto.*;
import com.safa.enviovital.excepciones.NotFoundException.UsernameAlredyExistsException;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.modelos.Conductor;
import com.safa.enviovital.servicios.ConductorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import java.util.List;

@RestController
@RequestMapping("/conductores")
@AllArgsConstructor
public class ConductorControlador {

    private final ConductorService conductorService;

    @Operation(summary = "Obtén la lista de todos los conductores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se han obtenido la lista de los conductores correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConductorResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la lista de conductores", content = @Content)
    })
    @GetMapping("/lista")
    public ResponseEntity<List<ConductorResponseDTO>> listarConductores() {
        return ResponseEntity.ok(conductorService.getAll());
    }

    @Operation(summary = "Obtén un conductor por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha obtenido el conductor correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConductorResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el conductor", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ConductorResponseDTO> obtenerConductorPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(conductorService.getConductorPorId(id));
    }

    @Operation(summary = "Obtén un conductor por el ID del usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha obtenido el conductor correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConductorResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el conductor", content = @Content)
    })
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<ConductorResponseDTO> getConductorByUsuarioId(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(conductorService.getConductorByUsuarioId(idUsuario));
    }

    @Operation(summary = "Guarda un nuevo conductor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha guardado el conductor correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConductorResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el conductor", content = @Content)
    })
    @PostMapping("/guardar")
    public ResponseEntity<ConductorResponseDTO> guardarConductor(@RequestBody ConductorRequestDTO requestDTO) throws UsernameAlredyExistsException {
        return ResponseEntity.ok(ConductorResponseDTO.ConductorResponseDtoFromConductor(conductorService.guardar(requestDTO)));
    }

    @Operation(summary = "Edita un conductor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha editado el conductor correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConductorResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el conductor", content = @Content)
    })
    @PutMapping("/editar/{id}")
    public ResponseEntity<ConductorResponseDTO> editarConductor(@PathVariable Integer id, @RequestBody ConductorRequestDTO requestDTO) {
        return ResponseEntity.ok(ConductorResponseDTO.ConductorResponseDtoFromConductor(conductorService.editar(id, requestDTO)));
    }

    @Operation(summary = "Elimina un conductor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Se ha eliminado el conductor correctamente", content = @Content),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el conductor", content = @Content)
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarConductor(@PathVariable Integer id) {
        conductorService.borrarConductor(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Registra un conductor en un EventoAlmacen")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha registrado el conductor en el EventoAlmacen correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el EventoAlmacen o el conductor", content = @Content)
    })
    @PostMapping("/registrarse/{eventoAlmacenId}/{conductorId}")
    public ResponseEntity<Response> registrarConductorEnEventoAlmacen(
            @PathVariable Integer eventoAlmacenId,
            @PathVariable Integer conductorId) {
        return conductorService.registrarConductorEnEventoAlmacen(eventoAlmacenId, conductorId);
    }

    @Operation(summary = "Obtén los almacenes registrados por un conductor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se han obtenido los almacenes registrados correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ListaAlmacenesRegistradosByConductorDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se han encontrado los almacenes registrados", content = @Content)
    })
    @GetMapping("/almacenesRegistrados/{conductorId}")
    public ResponseEntity<List<ListaAlmacenesRegistradosByConductorDTO>> listarAlmacenesRegistradosByConductor(@PathVariable Integer conductorId) {
        return ResponseEntity.ok(conductorService.obtenerEventoAlmacenPorConductor(conductorId));
    }

    @Operation(summary = "Elimina el registro de un conductor en un EventoAlmacen")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha eliminado el registro correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el registro", content = @Content)
    })
    @DeleteMapping("/eliminarRegistro/{eventoAlmacenConductorId}")
    public ResponseEntity<Response> eliminarRegistro(@PathVariable Integer eventoAlmacenConductorId) {
        return ResponseEntity.ok(conductorService.eliminarRegistroConductorEnEventoAlmacen(eventoAlmacenConductorId));
    }

    @Operation(summary = "Obtén los vehículos registrados de un conductor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se han obtenido los vehículos registrados correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = VehiculoResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se han encontrado los vehículos registrados", content = @Content)
    })
    @GetMapping("/vehiculosRegistrados/{conductorId}")
    public ResponseEntity<List<VehiculoResponseDTO>> listarVehiculosRegistradosByConductor(@PathVariable Integer conductorId) {
        return ResponseEntity.ok(conductorService.getVehiculosByConductorId(conductorId));
    }

    @Operation(summary = "Cambia el estado de un conductor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha cambiado el estado del conductor correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConductorResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el conductor", content = @Content)
    })
    @PutMapping("/estado/{id}")
    private ResponseEntity<ConductorResponseDTO> changeConductorState(@PathVariable int id){
        return ResponseEntity.ok(ConductorResponseDTO.ConductorResponseDtoFromConductor(conductorService.cambiarEstadoConductor(id)));
    }
}
