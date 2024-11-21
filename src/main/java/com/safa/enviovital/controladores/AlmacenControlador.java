package com.safa.enviovital.controladores;

import com.safa.enviovital.dto.*;
import com.safa.enviovital.excepciones.NotFoundException.UsernameAlredyExistsException;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.servicios.AlmacenService;
import com.safa.enviovital.servicios.ConductorService;
import lombok.AllArgsConstructor;
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
    @Autowired
    private final ConductorService conductorService;



    /**
     * Endpoint para obtener todos los almacenes.
     * @return Lista de almacenes
     */
    @GetMapping("/lista")
    public ResponseEntity<List<AlmacenResponseDTO>> listarAlmacenes() {
        return ResponseEntity.ok(almacenService.getAll());
    }

    /**
     * Endpoint para obtener un almacén por su ID.
     * @param id ID del almacén
     * @return AlmacenResponseDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<AlmacenResponseDTO> obtenerAlmacenPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(almacenService.getAlmacenPorId(id));
    }

    /**
     * Endpoint para guardar un nuevo almacén.
     * @param requestDTO Datos del almacén a guardar
     * @return AlmacenResponseDTO con los datos del almacén guardado
     */
    @PostMapping("/guardar")
    public ResponseEntity<AlmacenResponseDTO> guardarAlmacen(@RequestBody AlmacenRequestDTO requestDTO)throws UsernameAlredyExistsException {
        return ResponseEntity.ok(almacenService.guardar(requestDTO));
    }

    /**
     * Endpoint para editar un almacén.
     * @param id ID del almacén a editar
     * @param requestDTO Datos del almacén a editar
     * @return AlmacenResponseDTO con los datos del almacén editado
     */
    @PutMapping("/editar/{id}")
    public ResponseEntity<AlmacenResponseDTO> editarAlmacen(@PathVariable Integer id, @RequestBody AlmacenEditarDTO requestDTO) {
        return ResponseEntity.ok(almacenService.editar(id, requestDTO));
    }

    /**
     * Endpoint para eliminar un almacén.
     * @param id ID del almacén a eliminar
     * @return Response con el mensaje de eliminación
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response> eliminarAlmacen(@PathVariable Integer id) {
        Response respuesta = almacenService.eliminar(id);
        return ResponseEntity.status(respuesta.getStatusCode()).body(respuesta);
    }

    /**
     * Endpoint para registrar un almacén en un evento.
     * @param idEvento ID del evento
     * @param idAlmacen ID del almacén
     * @return ResponseEntity con el mensaje de éxito
     */
    @PostMapping("/registrarse/{idEvento}/{idAlmacen}")
    public ResponseEntity<Response> registrarAlmacenEnEvento(@PathVariable Integer idEvento, @PathVariable Integer idAlmacen) {
        return almacenService.registrarAlmacenEnEvento(idEvento, idAlmacen);
    }

    @GetMapping("/listaregistrados/{idEvento}")
    public ResponseEntity<List<EventoAlmacenDtoResponse>> obtenerAlmacenesPorEvento(@PathVariable Integer idEvento) {
        List<EventoAlmacenDtoResponse> eventoAlmacenes = almacenService.obtenerEventoAlmacenPorEvento(idEvento);
        return ResponseEntity.ok(eventoAlmacenes);
    }

    @GetMapping("/listaEventos/{idAlmacen}")
    public ResponseEntity<List<ListaEventosByAlmacenDTO>> obtenerEventosPorAlmacen(@PathVariable Integer idAlmacen){
        List<ListaEventosByAlmacenDTO> eventoAlmacenes = almacenService.obtenerEventoAlmacenPorAlmacen(idAlmacen);
        return ResponseEntity.ok(eventoAlmacenes);
    }

    @DeleteMapping("/eliminarRegistro/{eventoAlmacenId}")
    public ResponseEntity<Response> eliminarRegistro(@PathVariable Integer eventoAlmacenId) {
        return ResponseEntity.ok(almacenService.eliminarRegistroAlmacenEnEvento(eventoAlmacenId));
    }





}
