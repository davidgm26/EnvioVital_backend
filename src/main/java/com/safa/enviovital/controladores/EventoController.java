package com.safa.enviovital.controladores;

import com.safa.enviovital.dto.EventoRequestDto;
import com.safa.enviovital.dto.EventoResponseDto;
import com.safa.enviovital.modelos.Evento;
import com.safa.enviovital.servicios.EventoService;
import lombok.AllArgsConstructor;
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

    @GetMapping("/")
    private ResponseEntity<List<EventoResponseDto>> getAllEventos(){
     return ResponseEntity.ok(eventoService.getAllEventos());
    }

    @GetMapping("/activos")
    private ResponseEntity<List<EventoResponseDto>> getAllEventosActivos(){
        return ResponseEntity.ok(eventoService.getAllActivos());
    }

    @GetMapping("/{id}")
    private ResponseEntity<EventoResponseDto> getEventoById(@PathVariable int id){
        return ResponseEntity.ok(EventoResponseDto.EventoResponseDtoFromEvento(eventoService.getEventoById(id)));
    }

    @GetMapping("/provincia/{id}")
    private ResponseEntity<List<EventoResponseDto>> getEventosByProvincia(@PathVariable int id){
        return ResponseEntity.ok(eventoService.getEventoByProvincia(id));
    }

    @PostMapping("/")
    private ResponseEntity<Evento> createEvento(@RequestBody EventoRequestDto eventoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoService.createEvento(eventoRequest));
    }

    @PutMapping("/{id}")
    private ResponseEntity<EventoResponseDto> editarEvento(@PathVariable int id,@RequestBody EventoRequestDto eventoRequest){
        return ResponseEntity.ok(eventoService.editarEvento(id,eventoRequest));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> eliminarEvento(@PathVariable int id){
        eventoService.eliminarEvento(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/estado/{id}")
    private ResponseEntity<EventoResponseDto> changeEventoStatus(@PathVariable int id){
        return ResponseEntity.ok(EventoResponseDto.EventoResponseDtoFromEvento(eventoService.cambiarEstadoEvento(id)));
    }








}
