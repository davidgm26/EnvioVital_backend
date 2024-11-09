package com.safa.enviovital.controladores;

import com.safa.enviovital.modelos.Evento;
import com.safa.enviovital.servicios.EventoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ResponseEntity<List<Evento>> getAllEventos(){
     return ResponseEntity.ok(eventoService.getAllEventos());
    }

    @GetMapping("/{id}<")
    private ResponseEntity<Evento> getEventoById(@PathVariable int id){
        return ResponseEntity.ok(eventoService.getEventoById(id));
    }

    @PostMapping("/")
    private ResponseEntity<Evento> createEvento(@RequestBody Evento evento){
        return ResponseEntity.ok(eventoService.createEvento(evento));
    }












}
