package com.safa.enviovital.servicios;

import com.safa.enviovital.dto.ProvinciaDTO;
import com.safa.enviovital.excepciones.NotFoundException.ProvinciaNotFoundException;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.modelos.Provincia;
import com.safa.enviovital.repositorios.ProvinciaRepositorio;
import jakarta.persistence.EntityNotFoundException;
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
     * @return Lista de ProvinciaDTO
     */
    public List<ProvinciaDTO> getAll() {
        return provinciaRepositorio.findAllProvinciasDTO();
    }

    /**
     * Método que retorna una provincia por su ID.
     * @param id ID de la provincia
     * @return ProvinciaDTO
     * @throws ProvinciaNotFoundException si no se encuentra la provincia
     */
    public ProvinciaDTO getProvinciaPorId(Integer id) {
        return provinciaRepositorio.findProvinciaById(id)
                .orElseThrow(() -> new ProvinciaNotFoundException(id));
    }

    /**
     * Método que guarda una nueva provincia.
     * @param provinciaRequestDTO Datos de la nueva provincia
     * @return ProvinciaDTO
     */
    public ProvinciaDTO guardar(ProvinciaDTO provinciaRequestDTO) {
        Provincia nuevaProvincia = Provincia.builder()
                .nombre(provinciaRequestDTO.getNombre())
                .build();
        return ProvinciaDTO.createProvinciaDTOFromProvincia(nuevaProvincia);
    }

    /**
     * Método que actualiza una provincia existente.
     * @param id ID de la provincia a actualizar
     * @param provinciatDTO Datos actualizados
     * @return ProvinciaDTO
     * @throws ProvinciaNotFoundException si no se encuentra la provincia
     */
    public ProvinciaDTO actualizar(Integer id, ProvinciaDTO provinciatDTO) {
        Provincia provinciaExistente = provinciaRepositorio.findById(id)
                .orElseThrow(() -> new ProvinciaNotFoundException(id));
        provinciaExistente.setNombre(provinciatDTO.getNombre());
        provinciaRepositorio.save(provinciaExistente);
        return ProvinciaDTO.createProvinciaDTOFromProvincia(provinciaExistente);
    }

    /**
     * Metodo que retorna una provincia por su id
     * @param id
     * @return
     */
    public Provincia getProvinciaById(Integer id) {
        return provinciaRepositorio.findById(id).orElseThrow(() -> new ProvinciaNotFoundException(id));
    
    }
    /*
     * @param id ID de la provincia a eliminar
     * @return Response con el mensaje de éxito
     * @throws ProvinciaNotFoundException si no se encuentra la provincia
     */

    public Response eliminar(Integer id) {
        Provincia provincia = provinciaRepositorio.findById(id)
                .orElseThrow(() -> new ProvinciaNotFoundException(id));
        provinciaRepositorio.delete(provincia);
        return new Response(
                "Provincia con ID " + id + " ha sido eliminada exitosamente",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
    }
}
