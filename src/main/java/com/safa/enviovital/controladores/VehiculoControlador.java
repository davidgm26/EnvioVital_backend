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

import java.util.List;

@RestController
@RequestMapping("/vehiculos")
@AllArgsConstructor
public class VehiculoControlador {

    @Autowired
    private final VehiculoService vehiculoService;

    @GetMapping("/lista")
    public ResponseEntity<List<VehiculoResponseDTO>> listarVehiculos() {
        return ResponseEntity.ok(vehiculoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoResponseDTO> obtenerVehiculoPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(vehiculoService.getVehiculoPorId(id));
    }

    @PostMapping("/guardar")
    public ResponseEntity<VehiculoResponseDTO> guardarVehiculo(@RequestBody VehiculoRequestDTO requestDTO) {
        return ResponseEntity.ok(vehiculoService.guardar(requestDTO));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<VehiculoResponseDTO> editarVehiculo(@PathVariable Integer id, @RequestBody VehiculoRequestDTO requestDTO) {
        return ResponseEntity.ok(vehiculoService.editar(id, requestDTO));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response> eliminarVehiculo(@PathVariable Integer id) {
        Response respuesta = vehiculoService.eliminar(id);
        return ResponseEntity.status(respuesta.getStatusCode()).body(respuesta);
    }
}