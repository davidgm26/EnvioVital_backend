package com.safa.enviovital.servicios;

import com.safa.enviovital.dto.*;
import com.safa.enviovital.enumerados.Rol;
import com.safa.enviovital.excepciones.NotFoundException.ConductorNotFoundException;
import com.safa.enviovital.excepciones.NotFoundException.EventoAlmacenNotFoundException;
import com.safa.enviovital.excepciones.NotFoundException.UsernameAlredyExistsException;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.modelos.*;
import com.safa.enviovital.repositorios.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    // GET /conductores/usuario/{idUsuario} hazlo sin dto, usar el builder de ConductorResponse
    public ConductorResponseDTO getConductorByUsuarioId(Integer idUsuario) {
        Conductor conductor = conductorRepositorio.findConductorByUsuarioId(idUsuario)
                .orElseThrow(() -> new ConductorNotFoundException("No se ha encontrado ningún conductor con el usuario ID " + idUsuario));
        return ConductorResponseDTO.ConductorResponseDtoFromConductor(conductor);
    }

    public Conductor guardarConductor(Conductor c){
        return conductorRepositorio.save(c);
    }


    /**
     * Método para guardar un nuevo conductor.
     * @param requestDTO Datos del conductor a guardar
     * @return ConductorResponseDTO con los datos del conductor guardado
     */
    @Transactional    
    public Conductor guardar(ConductorRequestDTO requestDTO) throws UsernameAlredyExistsException {
        if(usuarioRepositorio.findTopByUsername(requestDTO.getUsuario().getUsername()).isPresent()){
            throw new UsernameAlredyExistsException(requestDTO.getUsuario().getUsername());
        }
        Usuario u = usuarioService.crearUsuario(requestDTO.getUsuario());
        var c =ConductorRequestDTO.conductorFromRequest(requestDTO);
        u.setRol(Rol.CONDUCTOR);
        c.setUsuario(u);
        return conductorRepositorio.save(c);

    }

    /**
     * Método para editar un conductor existente.
     * @param id ID del conductor a editar
     * @param dto Datos del conductor a editar
     * @return ConductorResponseDTO con los datos del conductor editado
     */
    public Conductor editar(Integer id, ConductorRequestDTO dto) {
        Conductor c  = conductorRepositorio.findById(id).orElseThrow(() -> new ConductorNotFoundException("Conductor no encontrado"));
        c.setNombre(dto.getNombre());
        c.setApellidos(dto.getApellidos());
        c.setDni(dto.getDni());
        c.setDireccion(dto.getDireccion());
        c.setTelefono(dto.getTelefono());
        c.setFechaNacimiento(dto.getFechaNacimiento());
        c.setEmail(dto.getEmail());
        return guardarConductor(c);
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

    public Conductor cambiarEstadoConductor(int id) {
        Conductor c = conductorRepositorio.findById(id).get();
        c.setEsActivo(!c.getEsActivo());
        return conductorRepositorio.save(c);
    }

    /**
     * Método para eliminar un conductor.
     * @param id ID del conductor a eliminar
     * @return Respuesta con el mensaje de eliminación
     */
    public void borrarConductor(int id) {
        Conductor c = conductorRepositorio.findById(id).orElseThrow(() -> new ConductorNotFoundException("Conductor no encontrado"));
        c.getVehiculos().remove(c);
        c.getEventoAlmacenConductores().remove(c);
        conductorRepositorio.delete(c);
    }


    public List<VehiculoResponseDTO> getVehiculosByConductorId(Integer id){
        List<Vehiculo> vehiculos = vehiculoRepositorio.findVehiculosByConductorId(id);
        return vehiculos.stream().map(VehiculoResponseDTO::VehiculoResponseDTOfromVehiculo).toList();
    }

}
