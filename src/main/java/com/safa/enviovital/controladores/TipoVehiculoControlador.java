package com.safa.enviovital.controladores;

import com.safa.enviovital.dto.TipoVehiculoRequestDTO;
import com.safa.enviovital.dto.TipoVehiculoResponseDTO;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.servicios.TipoVehiculoService;
import lombok.AllArgsConstructor;
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

    /**
     * Endpoint para obtener todos los tipos de vehículos.
     * @return Lista de tipos de vehículos
     */
    @GetMapping("/lista")
    public ResponseEntity<List<TipoVehiculoResponseDTO>> listarTiposVehiculo() {
        return ResponseEntity.ok(tipoVehiculoService.getAll());
    }

    /**
     * Endpoint para obtener un tipo de vehículo por su ID.
     *
     * @param id ID del tipo de vehículo
     * @return TipoVehiculoResponseDTO
     */
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

    /**
     * Endpoint para guardar un nuevo tipo de vehículo.
     * @param requestDTO Datos del tipo de vehículo a guardar
     * @return TipoVehiculoResponseDTO con los datos del tipo de vehículo guardado
     */
    @PostMapping("/guardar")
    public ResponseEntity<TipoVehiculoResponseDTO> guardarTipoVehiculo(@RequestBody TipoVehiculoRequestDTO requestDTO) {
        return ResponseEntity.ok(tipoVehiculoService.guardar(requestDTO));
    }

    /**
     * Endpoint para editar un tipo de vehículo.
     * @param id ID del tipo de vehículo a editar
     * @param requestDTO Datos del tipo de vehículo a editar
     * @return TipoVehiculoResponseDTO con los datos del tipo de vehículo editado
     */
    @PutMapping("/editar/{id}")
    public ResponseEntity<TipoVehiculoResponseDTO> editarTipoVehiculo(@PathVariable Integer id, @RequestBody TipoVehiculoRequestDTO requestDTO) {
        return ResponseEntity.ok(tipoVehiculoService.editar(id, requestDTO));
    }

    /**
     * Endpoint para eliminar un tipo de vehículo.
     * @param id ID del tipo de vehículo a eliminar
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response> eliminarTipoVehiculo(@PathVariable Integer id) {
        Response respuesta = tipoVehiculoService.eliminar(id);
        return ResponseEntity.status(respuesta.getStatusCode()).body(respuesta);
    }
}
