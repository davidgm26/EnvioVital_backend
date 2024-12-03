package com.safa.enviovital.controladores;

import com.safa.enviovital.dto.*;
import com.safa.enviovital.excepciones.NotFoundException.AlmacenNameAlredyExistsException;
import com.safa.enviovital.excepciones.NotFoundException.UsernameAlredyExistsException;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.modelos.Almacen;
import com.safa.enviovital.servicios.AlmacenService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping("/almacenes")
@AllArgsConstructor
public class AlmacenControlador {

    @Autowired
    private final AlmacenService almacenService;

    @Operation(summary = "Obtén la lista de todos los almacenes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se han obtenido la lista de los almacenes correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlmacenResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la lista de almacenes", content = @Content)
    })
    @GetMapping("/lista")
    public ResponseEntity<List<AlmacenResponseDTO>> listarAlmacenes() {
        return ResponseEntity.ok(almacenService.getAll());
    }

    @Operation(summary = "Obtén un almacén por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha obtenido el almacén correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlmacenResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el almacén", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AlmacenResponseDTO> obtenerAlmacenPorId(@PathVariable Integer id) {
        Almacen a = almacenService.getAlmacenPorId(id);
        return ResponseEntity.ok(AlmacenResponseDTO.AlmacenResponseDtoFromAlmacen(a));
    }

    @Operation(summary = "Obtén un almacén por el ID del usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha obtenido el almacén correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlmacenResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el almacén", content = @Content)
    })
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<AlmacenResponseDTO> getAlmacenByUsuarioId(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(almacenService.getAlmacenByUsuarioId(idUsuario));
    }

    @Operation(summary = "Guarda un nuevo almacén")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha guardado el almacén correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlmacenResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el almacén", content = @Content)
    })
    @PostMapping("/guardar")
    public ResponseEntity<AlmacenResponseDTO> guardarAlmacen(@RequestBody AlmacenRequestDTO requestDTO) throws AlmacenNameAlredyExistsException, UsernameAlredyExistsException {
        return ResponseEntity.ok(AlmacenResponseDTO.AlmacenResponseDtoFromAlmacen(almacenService.guardar(requestDTO)));
    }

    @Operation(summary = "Edita un almacén")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha editado el almacén correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlmacenResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el almacén", content = @Content)
    })
    @PutMapping("/editar/{id}")
    public ResponseEntity<AlmacenResponseDTO> editarAlmacen(@PathVariable Integer id, @RequestBody AlmacenEditarDTO requestDTO) {
        return ResponseEntity.ok(almacenService.editar(id, requestDTO));
    }

    @Operation(summary = "Elimina un almacén")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Se ha eliminado el almacén correctamente", content = @Content),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el almacén", content = @Content)
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response> eliminarAlmacen(@PathVariable Integer id) {
        Response respuesta = almacenService.eliminar(id);
        return ResponseEntity.status(respuesta.getStatusCode()).body(respuesta);
    }

    @Operation(summary = "Registra un almacén en un evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha registrado el almacén en el evento correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el evento o el almacén", content = @Content)
    })
    @PostMapping("/registrarse/{idEvento}/{idAlmacen}")
    public ResponseEntity<Response> registrarAlmacenEnEvento(@PathVariable Integer idEvento, @PathVariable Integer idAlmacen) {
        return almacenService.registrarAlmacenEnEvento(idEvento, idAlmacen);
    }

    @Operation(summary = "Obtén los almacenes registrados por evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se han obtenido los almacenes registrados correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventoAlmacenDtoResponse.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se han encontrado los almacenes registrados", content = @Content)
    })
    @GetMapping("/listaregistrados/{idEvento}")
    public ResponseEntity<List<EventoAlmacenDtoResponse>> obtenerAlmacenesPorEvento(@PathVariable Integer idEvento) {
        List<EventoAlmacenDtoResponse> eventoAlmacenes = almacenService.obtenerEventoAlmacenPorEvento(idEvento);
        return ResponseEntity.ok(eventoAlmacenes);
    }

    @Operation(summary = "Obtén los eventos registrados por almacén")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se han obtenido los eventos registrados correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ListaEventosByAlmacenDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se han encontrado los eventos registrados", content = @Content)
    })
    @GetMapping("/listaEventos/{idAlmacen}")
    public ResponseEntity<List<ListaEventosByAlmacenDTO>> obtenerEventosPorAlmacen(@PathVariable Integer idAlmacen) {
        List<ListaEventosByAlmacenDTO> eventoAlmacenes = almacenService.obtenerEventoAlmacenPorAlmacen(idAlmacen);
        return ResponseEntity.ok(eventoAlmacenes);
    }

    @Operation(summary = "Elimina el registro de un almacén en un evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha eliminado el registro correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el registro", content = @Content)
    })
    @DeleteMapping("/eliminarRegistro/{eventoAlmacenId}")
    public ResponseEntity<Response> eliminarRegistro(@PathVariable Integer eventoAlmacenId) {
        return ResponseEntity.ok(almacenService.eliminarRegistroAlmacenEnEvento(eventoAlmacenId));
    }

    @Operation(summary = "Obtén los conductores registrados por almacén")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se han obtenido los conductores registrados correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConductorResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se han encontrado los conductores registrados", content = @Content)
    })
    @GetMapping("/listaConductores/{almacenId}")
    public ResponseEntity<List<ConductorResponseDTO>> obtenerConductoresPorAlmacen(@PathVariable Integer almacenId) {
        List<ConductorResponseDTO> conductores = almacenService.obtenerConductoresPorAlmacen(almacenId);
        return ResponseEntity.ok(conductores);
    }

    @Operation(summary = "Cambia el estado de un almacén")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha cambiado el estado del almacén correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AlmacenResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el almacén", content = @Content)
    })
    @PutMapping("/estado/{id}")
    public ResponseEntity<AlmacenResponseDTO> changeAlmacenState(@PathVariable Integer id) {
        return ResponseEntity.ok(AlmacenResponseDTO.AlmacenResponseDtoFromAlmacen(almacenService.changeAlmacenState(id)));
    }
}