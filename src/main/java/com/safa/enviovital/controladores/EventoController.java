package com.safa.enviovital.controladores;

import com.safa.enviovital.dto.EventoRequestDto;
import com.safa.enviovital.dto.EventoResponseDto;
import com.safa.enviovital.modelos.Evento;
import com.safa.enviovital.servicios.EventoService;
import jakarta.persistence.NamedEntityGraph;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evento")
@AllArgsConstructor
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Operation(summary = "Obtén la lista de todos los eventos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se han obtenido la lista de los eventos correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventoResponseDto.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la lista de eventos", content = @Content)
    })
    @GetMapping("/")
    private ResponseEntity<List<EventoResponseDto>> getAllEventos() {
        return ResponseEntity.ok(eventoService.getAllEventos());
    }

    @Operation(summary = "Obtén la lista de todos los eventos activos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se han obtenido la lista de los eventos activos correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventoResponseDto.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado la lista de eventos activos", content = @Content)
    })
    @GetMapping("/activos")
    private ResponseEntity<List<EventoResponseDto>> getAllEventosActivos() {
        return ResponseEntity.ok(eventoService.getAllActivos());
    }

    @Operation(summary = "Obtén un evento por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha obtenido el evento correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventoResponseDto.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el evento", content = @Content)
    })
    @GetMapping("/{id}")
    private ResponseEntity<EventoResponseDto> getEventoById(@PathVariable int id) {
        return ResponseEntity.ok(EventoResponseDto.EventoResponseDtoFromEvento(eventoService.getEventoById(id)));
    }

    @Operation(summary = "Obtén los eventos por provincia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se han obtenido los eventos por provincia correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventoResponseDto.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se han encontrado los eventos por provincia", content = @Content)
    })
    @GetMapping("/provincia/{id}")
    private ResponseEntity<List<EventoResponseDto>> getEventosByProvincia(@PathVariable int id) {
        return ResponseEntity.ok(eventoService.getEventoByProvincia(id));
    }

    @Operation(summary = "Crea un nuevo evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se ha creado el evento correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Evento.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el evento", content = @Content)
    })
    @PostMapping("/")
    private ResponseEntity<Evento> createEvento(@RequestBody EventoRequestDto eventoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoService.createEvento(eventoRequest));
    }

    @Operation(summary = "Edita un evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha editado el evento correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventoResponseDto.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el evento", content = @Content)
    })
    @PutMapping("/{id}")
    private ResponseEntity<EventoResponseDto> editarEvento(@PathVariable int id, @RequestBody EventoRequestDto eventoRequest) {
        return ResponseEntity.ok(eventoService.editarEvento(id, eventoRequest));
    }

    @Operation(summary = "Elimina un evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Se ha eliminado el evento correctamente", content = @Content),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el evento", content = @Content)
    })
    @DeleteMapping("/{id}")
    private ResponseEntity<?> eliminarEvento(@PathVariable int id) {
        eventoService.eliminarEvento(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Cambia el estado de un evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha cambiado el estado del evento correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EventoResponseDto.class))}),
            @ApiResponse(responseCode = "401", description = "No tienes autorización para realizar esta petición", content = @Content),
            @ApiResponse(responseCode = "403", description = "Se ha expirado el token JWT o no tienes acceso para realizar esta petición debido a tu rol", content = @Content),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado el evento", content = @Content)
    })
    @PutMapping("/estado/{id}")
    private ResponseEntity<EventoResponseDto> changeEventoStatus(@PathVariable int id) {
        return ResponseEntity.ok(EventoResponseDto.EventoResponseDtoFromEvento(eventoService.cambiarEstadoEvento(id)));
    }
}