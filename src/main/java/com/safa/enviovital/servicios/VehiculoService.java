package com.safa.enviovital.servicios;

import com.safa.enviovital.dto.VehiculoRequestDTO;
import com.safa.enviovital.dto.VehiculoResponseDTO;
import com.safa.enviovital.excepciones.NotFoundException.VehiculoNotFoundException;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.modelos.Vehiculo;
import com.safa.enviovital.repositorios.VehiculoRepositorio;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VehiculoService {

    @Autowired
    private final VehiculoRepositorio vehiculoRepositorio;

    @Autowired
    private final ConductorService conductorService;

    @Autowired
    private final TipoVehiculoService tipoVehiculoService;

    /**
     * Método para obtener todos los vehículos.
     *
     * @return Lista de VehiculoResponseDTO
     */
    public List<VehiculoResponseDTO> getAll() {
        List<Vehiculo> vehiculos = vehiculoRepositorio.findAll();
        return vehiculos.stream()
                .map(VehiculoResponseDTO::VehiculoResponseDTOfromVehiculo)
                .collect(Collectors.toList());
    }

    /**
     * Método para obtener un vehículo por su ID.
     *
     * @param id ID del vehículo
     * @return VehiculoResponseDTO
     */
    public VehiculoResponseDTO getVehiculoPorId(Integer id) {
        Vehiculo vehiculo = vehiculoRepositorio.findById(id)
                .orElseThrow(() -> new VehiculoNotFoundException(id.toString()));
        return VehiculoResponseDTO.VehiculoResponseDTOfromVehiculo(vehiculo);
    }

    /**
     * Método para guardar un nuevo vehículo.
     *
     * @param requestDTO Datos del vehículo a guardar
     * @return VehiculoResponseDTO con los datos del vehículo guardado
     */
    @Transactional
    public VehiculoResponseDTO guardar(VehiculoRequestDTO requestDTO) {
        try {
            Vehiculo vehiculo = Vehiculo.builder()
                    .marca(requestDTO.getMarca())
                    .modelo(requestDTO.getModelo())
                    .matricula(requestDTO.getMatricula())
                    .conductor(conductorService.fromDTO(conductorService.getConductorPorId(requestDTO.getIdConductor())))
                    .tipoVehiculo(tipoVehiculoService.fromDTO(tipoVehiculoService.getTipoVehiculoPorId(requestDTO.getIdTipoVehiculo())))
                    .build();

            vehiculoRepositorio.save(vehiculo);

            return VehiculoResponseDTO.VehiculoResponseDTOfromVehiculo(vehiculo);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error de validación al guardar el vehículo", e);
        }
    }

    /**
     * Método para editar un vehículo existente.
     *
     * @param id ID del vehículo a editar
     * @param requestDTO Datos del vehículo a editar
     * @return VehiculoResponseDTO con los datos del vehículo editado
     */
    @Transactional
    public VehiculoResponseDTO editar(Integer id, VehiculoRequestDTO requestDTO) {
        Vehiculo vehiculo = vehiculoRepositorio.findById(id)
                .orElseThrow(() -> new VehiculoNotFoundException(id.toString()));

        vehiculo.setMarca(requestDTO.getMarca());
        vehiculo.setModelo(requestDTO.getModelo());
        vehiculo.setMatricula(requestDTO.getMatricula());
        vehiculo.setConductor(conductorService.fromDTO(conductorService.getConductorPorId(requestDTO.getIdConductor())));
        vehiculo.setTipoVehiculo(tipoVehiculoService.fromDTO(tipoVehiculoService.getTipoVehiculoPorId(requestDTO.getIdTipoVehiculo())));
        vehiculoRepositorio.save(vehiculo);

        return VehiculoResponseDTO.VehiculoResponseDTOfromVehiculo(vehiculo);
    }

    /**
     * Método para eliminar un vehículo.
     *
     * @param id ID del vehículo a eliminar
     * @return Respuesta con el mensaje de eliminación
     */
    @Transactional
    public Response eliminar(Integer id) {
        Vehiculo vehiculo = vehiculoRepositorio.findById(id)
                .orElseThrow(() -> new VehiculoNotFoundException(id.toString()));

        vehiculoRepositorio.delete(vehiculo);

        return new Response(
                "Vehículo con ID " + id + " ha sido eliminado exitosamente",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
    }

    /**
     * Método para manejar posibles errores de validación o conflicto.
     *
     * @param exception Excepción capturada
     * @return ResponseEntity con el mensaje de error
     */
    public ResponseEntity<Response> manejarExcepciones(Exception exception) {
        Response response = new Response(
                "Ocurrió un error: " + exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
