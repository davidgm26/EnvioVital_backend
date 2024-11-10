package com.safa.enviovital.controladores;

import com.safa.enviovital.dto.ProvinciaRequestDTO;
import com.safa.enviovital.dto.ProvinciaResponseDTO;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.servicios.ProvinciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provincias")
@AllArgsConstructor
public class ProvinciaControlador {

    private final ProvinciaService provinciaService;

    /**
     * Endpoint para obtener todas las provincias.
     * @return Lista de provincias
     */
    @GetMapping("/lista")
    public List<Provincia> getAllAlmacenes() {
    public ResponseEntity<List<ProvinciaResponseDTO>> listarProvincias() {
        return ResponseEntity.ok(provinciaService.getAll());
    }

    /**
     * Endpoint para obtener una provincia por su ID.
     * @param id ID de la provincia
     * @return ProvinciaResponseDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProvinciaResponseDTO> obtenerProvinciaPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(provinciaService.getProvinciaPorId(id));
    }



    @PostMapping("/new")
    public Provincia guardar(@RequestBody Provincia provincia) {
        return  provinciaService.guardar(provincia);
  }



    /**
     * Endpoint para guardar una nueva provincia.
     * @param requestDTO Datos de la provincia a guardar
     * @return ProvinciaResponseDTO con los datos de la provincia guardada
     */
    @PostMapping("/guardar")
    public ResponseEntity<ProvinciaResponseDTO> guardarProvincia(@RequestBody ProvinciaRequestDTO requestDTO) {
        return ResponseEntity.ok(provinciaService.guardar(requestDTO));
    }

    /**
     * Endpoint para editar una provincia.
     * @param id ID de la provincia a editar
     * @param requestDTO Datos de la provincia a editar
     * @return ProvinciaResponseDTO con los datos de la provincia editada
     */
    @PutMapping("/editar/{id}")
    public ResponseEntity<ProvinciaResponseDTO> editarProvincia(@PathVariable Integer id, @RequestBody ProvinciaRequestDTO requestDTO) {
        return ResponseEntity.ok(provinciaService.actualizar(id, requestDTO));
    }

    /**
     * Endpoint para eliminar una provincia.
     * @param id ID de la provincia a eliminar
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response> eliminarProvincia(@PathVariable Integer id) {
        Response respuesta = provinciaService.eliminar(id);
        return ResponseEntity.status(respuesta.getStatusCode()).body(respuesta);
    }
}
