package com.safa.enviovital.servicios;

import com.safa.enviovital.dto.TipoVehiculoRequestDTO;
import com.safa.enviovital.dto.TipoVehiculoResponseDTO;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.excepciones.NotFoundException.TipoVehiculoNotFoundException;
import com.safa.enviovital.modelos.TipoVehiculo;
import com.safa.enviovital.repositorios.TipoVehiculoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TipoVehiculoService {

    private final TipoVehiculoRepositorio tipoVehiculoRepositorio;

    /**
     * Método que retorna todos los tipos de vehículo sin usar streams ni bucles.
     * @return Lista de TipoVehiculoResponseDTO
     */
    public List<TipoVehiculoResponseDTO> getAll() {
        // Usamos el repositorio para obtener directamente los DTOs
        return tipoVehiculoRepositorio.findAllTipoVehiculoResponseDTO();
    }

    /**
     * Método que retorna un tipo de vehículo por su ID sin usar streams ni bucles.
     *
     * @param id ID del tipo de vehículo
     * @return TipoVehiculoResponseDTO o excepción si no existe
     */
    public TipoVehiculoResponseDTO getTipoVehiculoPorId(Integer id) {
        // Usamos el repositorio para obtener el DTO directamente
        return tipoVehiculoRepositorio.findTipoVehiculoById(id)
                .orElseThrow(() -> new TipoVehiculoNotFoundException("El tipo de vehículo con ID " + id + " no existe"));
    }
    /**
     * Método para guardar un nuevo tipo de vehículo.
     * @param requestDTO Datos de tipo de vehículo a guardar
     * @return TipoVehiculoResponseDTO con los datos del tipo de vehículo guardado
     */
    public TipoVehiculoResponseDTO guardar(TipoVehiculoRequestDTO requestDTO) {
        TipoVehiculo tipoVehiculo = new TipoVehiculo();
        tipoVehiculo.setNombre(requestDTO.getNombre());
        TipoVehiculo tipoVehiculoGuardado = tipoVehiculoRepositorio.save(tipoVehiculo);
        return new TipoVehiculoResponseDTO(tipoVehiculoGuardado.getId(), tipoVehiculoGuardado.getNombre());
    }

    /**
     * Método para editar un tipo de vehículo existente.
     * @param id ID del tipo de vehículo a editar
     * @param requestDTO Datos a actualizar
     * @return TipoVehiculoResponseDTO con los datos del tipo de vehículo actualizado
     */
    public TipoVehiculoResponseDTO editar(Integer id, TipoVehiculoRequestDTO requestDTO) {
        TipoVehiculo tipoVehiculo = tipoVehiculoRepositorio.findById(id)
                .orElseThrow(() -> new TipoVehiculoNotFoundException("El tipo de vehículo con ID " + id + " no existe"));

        tipoVehiculo.setNombre(requestDTO.getNombre());
        TipoVehiculo tipoVehiculoEditado = tipoVehiculoRepositorio.save(tipoVehiculo);
        return new TipoVehiculoResponseDTO(tipoVehiculoEditado.getId(), tipoVehiculoEditado.getNombre());
    }

    /**
     * Método para eliminar un tipo de vehículo.
     * @param id ID del tipo de vehículo a eliminar
     */
    public Response eliminar(Integer id) {
        TipoVehiculo tipoVehiculo = tipoVehiculoRepositorio.findById(id)
                .orElseThrow(() -> new TipoVehiculoNotFoundException("El tipo de vehículo con ID " + id + " no existe"));

        tipoVehiculoRepositorio.delete(tipoVehiculo);

        return new Response(
                "Tipo de vehículo con ID " + id + " ha sido eliminado exitosamente",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
    }

    public TipoVehiculo fromDTO(TipoVehiculoResponseDTO tipoVehiculo) {
        return TipoVehiculo.builder()
                .id(tipoVehiculo.getId())
                .nombre(tipoVehiculo.getNombre())
                .build();
    }
}
