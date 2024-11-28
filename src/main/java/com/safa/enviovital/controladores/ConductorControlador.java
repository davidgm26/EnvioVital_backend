package com.safa.enviovital.controladores;

import com.safa.enviovital.dto.*;
import com.safa.enviovital.excepciones.NotFoundException.UsernameAlredyExistsException;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.servicios.ConductorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conductores")
@AllArgsConstructor
public class ConductorControlador {

    private final ConductorService conductorService;

    /**
     * Endpoint para obtener todos los conductores.
     * @return Lista de conductores
     */
    @GetMapping("/lista")
    public ResponseEntity<List<ConductorResponseDTO>> listarConductores() {
        return ResponseEntity.ok(conductorService.getAll());
    }

    /**
     * Endpoint para obtener un conductor por su ID.
     * @param id ID del conductor
     * @return ConductorResponseDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<ConductorResponseDTO> obtenerConductorPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(conductorService.getConductorPorId(id));
    }
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<ConductorResponseDTO> getConductorByUsuarioId(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(conductorService.getConductorByUsuarioId(idUsuario));
    }

    /**
     * Endpoint para guardar un nuevo conductor.
     * @param requestDTO Datos del conductor a guardar
     * @return ConductorResponseDTO con los datos del conductor guardado
     */
    @PostMapping("/guardar")
    public ResponseEntity<ConductorResponseDTO> guardarConductor(@RequestBody ConductorRequestDTO requestDTO) throws UsernameAlredyExistsException {
        return ResponseEntity.ok(conductorService.guardar(requestDTO));
    }

    /**
     * Endpoint para editar un conductor.
     * @param id ID del conductor a editar
     * @param requestDTO Datos del conductor a editar
     * @return ConductorResponseDTO con los datos del conductor editado
     */
    @PutMapping("/editar/{id}")
    public ResponseEntity<ConductorResponseDTO> editarConductor(@PathVariable Integer id, @RequestBody ConductorRequestDTO requestDTO) {
        return ResponseEntity.ok(conductorService.editar(id, requestDTO));
    }

    /**
     * Endpoint para eliminar un conductor.
     * @param id ID del conductor a eliminar
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarConductor(@PathVariable Integer id) {
        conductorService.borrarConductor(id);
        return ResponseEntity.noContent().build();    }

    /**
     * Endpoint para registrar un conductor en un EventoAlmacen.
     * @param eventoAlmacenId ID del EventoAlmacen
     * @param conductorId ID del conductor
     * @return ResponseEntity con el mensaje de éxito o error
     */
    @PostMapping("/registrarse/{eventoAlmacenId}/{conductorId}")
    public ResponseEntity<Response> registrarConductorEnEventoAlmacen(
            @PathVariable Integer eventoAlmacenId,
            @PathVariable Integer conductorId) {
        return conductorService.registrarConductorEnEventoAlmacen(eventoAlmacenId, conductorId);
    }

    @GetMapping("/almacenesRegistrados/{conductorId}")
    public ResponseEntity<List<ListaAlmacenesRegistradosByConductorDTO>> listarAlmacenesRegistradosByConductor(@PathVariable Integer conductorId) {
        return ResponseEntity.ok(conductorService.obtenerEventoAlmacenPorConductor(conductorId));
    }

    @DeleteMapping("/eliminarRegistro/{eventoAlmacenConductorId}")
    public ResponseEntity<Response> eliminarRegistro(@PathVariable Integer eventoAlmacenConductorId) {
        return ResponseEntity.ok(conductorService.eliminarRegistroConductorEnEventoAlmacen(eventoAlmacenConductorId));
    }

    // endpoint para obtener los vehiculos registrados de un conductor

    @GetMapping("/vehiculosRegistrados/{conductorId}")
    public ResponseEntity<List<VehiculoResponseDTO>> listarVehiculosRegistradosByConductor(@PathVariable Integer conductorId) {
        return ResponseEntity.ok(conductorService.getVehiculosByConductorId(conductorId));
    }

    @PutMapping("/estado/{id}")
    private ResponseEntity<ConductorResponseDTO> changeConductorStatus(@PathVariable int id){
        return ResponseEntity.ok(ConductorResponseDTO.ConductorResponseDtoFromConductor(conductorService.cambiarEstadoConductor(id)));
    }



}
