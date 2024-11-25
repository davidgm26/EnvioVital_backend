package com.safa.enviovital.servicios;

import com.safa.enviovital.dto.*;
import com.safa.enviovital.enumerados.Rol;
import com.safa.enviovital.excepciones.NotFoundException.ConductorNotFoundException;
import com.safa.enviovital.excepciones.NotFoundException.EventoAlmacenNotFoundException;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.modelos.*;
import com.safa.enviovital.repositorios.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConductorService {

    @Autowired
    private final ConductorRepositorio conductorRepositorio;

    private final UsuarioRepositorio usuarioRepositorio;


    private final EventoAlmacenRepositorio eventoAlmacenRepositorio;

    private final EventoAlmacenConductorRepositorio eventoAlmacenConductorRepositorio;

    private final AlmacenRepositorio almacenRepositorio;

    private final VehiculoRepositorio vehiculoRepositorio;

    @Autowired
    private  UsuarioService usuarioService;

    /**
     * Método para obtener todos los conductores.
     * @return Lista de conductores
     */
    public List<ConductorResponseDTO> getAll() {
        return conductorRepositorio.findAllConductoresWithDTO();
    }

    /**
     * Método para obtener un conductor por su ID.
     *
     * @param id ID del conductor
     * @return ConductorResponseDTO
     */
    public ConductorResponseDTO getConductorPorId(Integer id) {
        return conductorRepositorio.findConductorByIdWithDTO(id)
                .orElseThrow(() -> new ConductorNotFoundException(id));
    }

    /**
     * Método para guardar un nuevo conductor.
     * @param requestDTO Datos del conductor a guardar
     * @return ConductorResponseDTO con los datos del conductor guardado
     */
    @Transactional
    public ConductorResponseDTO guardar(ConductorRequestDTO requestDTO) {
        try {
            UsuarioRequestDTO usuarioDTO = requestDTO.getUsuario();
            Usuario usuario = new Usuario();
            usuario.setUsername(usuarioDTO.getUsername());
            usuario.setPassword(usuarioDTO.getPassword());
            usuario.setRol(Rol.CONDUCTOR);

            Conductor conductor = new Conductor();
            conductor.setNombre(requestDTO.getNombre());
            conductor.setApellidos(requestDTO.getApellidos());
            conductor.setDni(requestDTO.getDni());
            conductor.setDireccion(requestDTO.getDireccion());
            conductor.setTelefono(requestDTO.getTelefono());
            conductor.setFechaNacimiento(requestDTO.getFechaNacimiento());
            conductor.setEmail(requestDTO.getEmail());
            conductor.setUsuario(usuario);

            usuarioRepositorio.save(usuario);
            Conductor savedConductor = conductorRepositorio.save(conductor);

            return new ConductorResponseDTO(
                    savedConductor.getId(),
                    savedConductor.getNombre(),
                    savedConductor.getApellidos(),
                    savedConductor.getDni(),
                    savedConductor.getDireccion(),
                    savedConductor.getTelefono(),
                    savedConductor.getFechaNacimiento(),
                    savedConductor.getEmail(),
                    savedConductor.getUsuario().getId()
            );

        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error de validación al guardar el conductor y usuario", e);
        }
    }


    /**
     * Método para editar un conductor existente.
     * @param id ID del conductor a editar
     * @param requestDTO Datos del conductor a editar
     * @return ConductorResponseDTO con los datos del conductor editado
     */
    public ConductorResponseDTO editar(Integer id, ConductorRequestDTO requestDTO) {
        Conductor conductor = conductorRepositorio.findById(id)
                .orElseThrow(() -> new ConductorNotFoundException("El conductor con ID " + id + " no existe"));

        conductor.setNombre(requestDTO.getNombre());
        conductor.setApellidos(requestDTO.getApellidos());
        conductor.setDni(requestDTO.getDni());
        conductor.setDireccion(requestDTO.getDireccion());
        conductor.setTelefono(requestDTO.getTelefono());
        conductor.setFechaNacimiento(requestDTO.getFechaNacimiento());
        conductor.setEmail(requestDTO.getEmail());
        usuarioService.guardarUsuario(conductor.getUsuario());
        conductorRepositorio.save(conductor);

        return ConductorResponseDTO.ConductorResponseDtoFromConductor(conductor);
    }

    /**
     * Método para eliminar un conductor.
     * @param id ID del conductor a eliminar
     * @return Respuesta con el mensaje de eliminación
     */
    public Response eliminar(Integer id) {
        Conductor conductor = conductorRepositorio.findById(id)
                .orElseThrow(() -> new ConductorNotFoundException("El conductor con ID " + id + " no existe"));

        conductorRepositorio.delete(conductor);

        return new Response(
                "Conductor con ID " + id + " ha sido eliminado exitosamente",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
    }

    public ResponseEntity<Response> registrarConductorEnEventoAlmacen(Integer eventoAlmacenId, Integer conductorId) {
        EventoAlmacen eventoAlmacen = eventoAlmacenRepositorio.findById(eventoAlmacenId)
                .orElseThrow(() -> new EventoAlmacenNotFoundException("EventoAlmacen no encontrado"));

        Conductor conductor = conductorRepositorio.findById(conductorId)
                .orElseThrow(() -> new ConductorNotFoundException("Conductor no encontrado"));

        Optional<EventoAlmacenConductor> existingEventoAlmacenConductor = eventoAlmacenConductorRepositorio.findByEventoAlmacenIdAndConductorId(eventoAlmacenId, conductorId);
        if (existingEventoAlmacenConductor.isPresent()) {
            Response response = new Response(
                    "El conductor con ID " + conductorId + " ya está registrado en el EventoAlmacen con ID " + eventoAlmacenId + ".",
                    HttpStatus.BAD_REQUEST.value(),
                    LocalDateTime.now()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }


        EventoAlmacenConductor eventoAlmacenConductor = new EventoAlmacenConductor();
        eventoAlmacenConductor.setEventoAlmacen(eventoAlmacen);
        eventoAlmacenConductor.setConductor(conductor);


        eventoAlmacenConductorRepositorio.save(eventoAlmacenConductor);

        Response response = new Response(
                "El conductor con ID " + conductorId + " ha sido registrado exitosamente en el EventoAlmacen con ID " + eventoAlmacenId + ".",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }

    public List<ListaAlmacenesRegistradosByConductorDTO> obtenerEventoAlmacenPorConductor(Integer idConductor) {
        List<EventoAlmacenConductor> lista = eventoAlmacenConductorRepositorio.findEventoAlmacenConductorByConductorId(idConductor);
        return lista.stream().map(ListaAlmacenesRegistradosByConductorDTO::toDto).toList();
    }

    public Response eliminarRegistroConductorEnEventoAlmacen(Integer eventoAlmanceConductorId) {
        EventoAlmacenConductor eventoAlmacenConductor = eventoAlmacenConductorRepositorio.findById(eventoAlmanceConductorId)
                .orElseThrow(() -> new EventoAlmacenNotFoundException("EventoAlmacenConductor no encontrado"));

        eventoAlmacenConductorRepositorio.delete(eventoAlmacenConductor);

        return new Response(
                "Se ha eliminado correctamente el registro " + eventoAlmanceConductorId + ".",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
    }
    public Conductor fromDTO(ConductorResponseDTO dto) {
        return Conductor.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .apellidos(dto.getApellidos())
                .dni(dto.getDni())
                .direccion(dto.getDireccion())
                .telefono(dto.getTelefono())
                .fechaNacimiento(dto.getFechaNacimiento())
                .email(dto.getEmail())
                .build();
    }

    public List<VehiculoResponseDTO> getVehiculosByConductorId(Integer id){
        List<Vehiculo> vehiculos = vehiculoRepositorio.findVehiculosByConductorId(id);
        return vehiculos.stream().map(VehiculoResponseDTO::VehiculoResponseDTOfromVehiculo).toList();
    }

}
