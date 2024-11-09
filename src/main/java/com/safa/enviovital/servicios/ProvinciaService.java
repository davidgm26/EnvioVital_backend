package com.safa.enviovital.servicios;

import com.safa.enviovital.dto.ProvinciaRequestDTO;
import com.safa.enviovital.dto.ProvinciaResponseDTO;
import com.safa.enviovital.excepciones.NotFoundException.ProvinciaNotFoundException;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.modelos.Provincia;
import com.safa.enviovital.repositorios.ProvinciaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProvinciaService {

    private final ProvinciaRepositorio provinciaRepositorio;

    /**
     * Método que retorna todos los tipos de provincia.
     * @return Lista de ProvinciaResponseDTO
     */
    public List<ProvinciaResponseDTO> getAll() {
        return provinciaRepositorio.findAllProvinciasDTO();
    }

    /**
     * Método que retorna una provincia por su ID.
     * @param id ID de la provincia
     * @return ProvinciaResponseDTO
     * @throws ProvinciaNotFoundException si no se encuentra la provincia
     */
    public ProvinciaResponseDTO getProvinciaPorId(Integer id) {
        return provinciaRepositorio.findProvinciaById(id)
                .orElseThrow(() -> new ProvinciaNotFoundException("La provincia con ID " + id + " no existe"));
    }

    /**
     * Método que guarda una nueva provincia.
     * @param provinciaRequestDTO Datos de la nueva provincia
     * @return ProvinciaResponseDTO
     */
    public ProvinciaResponseDTO guardar(ProvinciaRequestDTO provinciaRequestDTO) {
        Provincia nuevaProvincia = new Provincia();
        nuevaProvincia.setNombre(provinciaRequestDTO.getNombre());
        nuevaProvincia = provinciaRepositorio.save(nuevaProvincia);
        return new ProvinciaResponseDTO(nuevaProvincia.getId(), nuevaProvincia.getNombre());
    }

    /**
     * Método que actualiza una provincia existente.
     * @param id ID de la provincia a actualizar
     * @param provinciaRequestDTO Datos actualizados
     * @return ProvinciaResponseDTO
     * @throws ProvinciaNotFoundException si no se encuentra la provincia
     */
    public ProvinciaResponseDTO actualizar(Integer id, ProvinciaRequestDTO provinciaRequestDTO) {
        Provincia provinciaExistente = provinciaRepositorio.findById(id)
                .orElseThrow(() -> new ProvinciaNotFoundException("La provincia con ID " + id + " no existe"));
        provinciaExistente.setNombre(provinciaRequestDTO.getNombre());
        provinciaExistente = provinciaRepositorio.save(provinciaExistente);
        return new ProvinciaResponseDTO(provinciaExistente.getId(), provinciaExistente.getNombre());
    }

    /**
     * Método para eliminar una provincia.
     * @param id ID de la provincia a eliminar
     * @return Response con el mensaje de éxito
     * @throws ProvinciaNotFoundException si no se encuentra la provincia
     */
    public Response eliminar(Integer id) {
        Provincia provincia = provinciaRepositorio.findById(id)
                .orElseThrow(() -> new ProvinciaNotFoundException("La provincia con ID " + id + " no existe"));
        provinciaRepositorio.delete(provincia);
        return new Response(
                "Provincia con ID " + id + " ha sido eliminada exitosamente",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
    }
}
