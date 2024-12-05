package com.safa.enviovital.servicios;

import com.safa.enviovital.dto.*;
import com.safa.enviovital.enumerados.Rol;
import com.safa.enviovital.excepciones.NotFoundException.ConductorNotFoundException;
import com.safa.enviovital.excepciones.NotFoundException.EventoAlmacenNotFoundException;
import com.safa.enviovital.excepciones.NotFoundException.UsernameAlredyExistsException;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.excepciones.YaInscritoEnEventoAlmacen;
import com.safa.enviovital.modelos.*;
import com.safa.enviovital.repositorios.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConductorService {

    @Autowired
    private ConductorRepositorio conductorRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private EventoAlmacenRepositorio eventoAlmacenRepositorio;

    @Autowired
    private EventoAlmacenConductorRepositorio eventoAlmacenConductorRepositorio;


    @Autowired
    private VehiculoRepositorio vehiculoRepositorio;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmailService emailService;
    @Autowired
    private AlmacenService almacenService;
    @Autowired
    private AlertaRepositorio alertaRepositorio;

    /**
     * Método para obtener todos los conductores.
     *
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

    public ConductorResponseDTO getConductorByUsuarioId(Integer idUsuario) {
        Conductor conductor = conductorRepositorio.findConductorByUsuarioId(idUsuario)
                .orElseThrow(() -> new ConductorNotFoundException("No se ha encontrado ningún conductor con el usuario ID " + idUsuario));
        return ConductorResponseDTO.ConductorResponseDtoFromConductor(conductor);
    }

    public Conductor guardarConductor(Conductor c) {
        return conductorRepositorio.save(c);
    }


    /**
     * Método para guardar un nuevo conductor.
     *
     * @param requestDTO Datos del conductor a guardar
     * @return ConductorResponseDTO con los datos del conductor guardado
     */
    @Transactional
    public Conductor guardar(ConductorRequestDTO requestDTO) throws UsernameAlredyExistsException {
        if (usuarioRepositorio.findTopByUsername(requestDTO.getUsuario().getUsername()).isPresent()) {
            throw new UsernameAlredyExistsException(requestDTO.getUsuario().getUsername());
        }
        Usuario u = usuarioService.crearUsuario(requestDTO.getUsuario());
        var c = ConductorRequestDTO.conductorFromRequest(requestDTO);
        u.setRol(Rol.CONDUCTOR);
        c.setUsuario(u);
        c.setEsActivo(true);
        emailService.sendRegistrationEmail(c.getEmail(), c.getNombre());
        return guardarConductor(c);

    }

    /**
     * Método para editar un conductor existente.
     *
     * @param id  ID del conductor a editar
     * @param dto Datos del conductor a editar
     * @return ConductorResponseDTO con los datos del conductor editado
     */
    public Conductor editar(Integer id, ConductorRequestDTO dto) {
        Conductor c = conductorRepositorio.findById(id).orElseThrow(() -> new ConductorNotFoundException("Conductor no encontrado"));
        c.setNombre(dto.getNombre());
        c.setApellidos(dto.getApellidos());
        c.setDni(dto.getDni());
        c.setDireccion(dto.getDireccion());
        c.setTelefono(dto.getTelefono());
        c.setFechaNacimiento(LocalDate.parse(dto.getFechaNacimiento()));
        c.setEmail(dto.getEmail());
        return guardarConductor(c);
    }

    @Transactional
    public ResponseEntity<EventoAlmacenConductorDto> registrarConductorEnEventoAlmacen(Integer eventoAlmacenId, Integer conductorId, Integer idAlmacen) throws YaInscritoEnEventoAlmacen {
        EventoAlmacen e = eventoAlmacenRepositorio.getById(eventoAlmacenId);
        List<EventoAlmacen> almacenesEvento = eventoAlmacenRepositorio.findAllByEventoId(e.getEvento().getId());
        Conductor c = conductorRepositorio.findById(conductorId).get();
        for (EventoAlmacen eventoAlmacen : almacenesEvento) {
            if (eventoAlmacen.getAlmacen().getId().equals(idAlmacen)) {
                if (conductorInscritoEnEventoAlmacen(conductorId, eventoAlmacenId, idAlmacen)) {
                    throw new YaInscritoEnEventoAlmacen(c.getNombre());
                }
                EventoAlmacenConductor eventoAlmacenConductor = new EventoAlmacenConductor();
                eventoAlmacenConductor.setEventoAlmacen(eventoAlmacen);
                eventoAlmacenConductor.setConductor(c);
                eventoAlmacenConductorRepositorio.save(eventoAlmacenConductor);

                // Crear y guardar la alerta
                Alerta alerta = new Alerta();
                alerta.setMensaje("El conductor " + c.getNombre() + " se ha registrado en el evento " + eventoAlmacen.getEvento().getNombre());
                alerta.setUsuario(eventoAlmacen.getAlmacen().getUsuario());
                alertaRepositorio.save(alerta);

                return ResponseEntity.ok(EventoAlmacenConductorDto.fromEntity(eventoAlmacenConductor));
            }
        }
        throw new YaInscritoEnEventoAlmacen(c.getNombre());
    }

    public List<ListaAlmacenesRegistradosByConductorDTO> obtenerEventoAlmacenPorConductor(Integer idConductor) {
        List<EventoAlmacenConductor> lista = eventoAlmacenConductorRepositorio.findEventoAlmacenConductorByConductorId(idConductor);
        return lista.stream().map(ListaAlmacenesRegistradosByConductorDTO::toDto).toList();
    }

    public Response eliminarRegistroConductorEnEventoAlmacen(Integer eventoAlmancenConductorId) {
        EventoAlmacenConductor eventoAlmacenConductor = eventoAlmacenConductorRepositorio.findById(eventoAlmancenConductorId)
                .orElseThrow(() -> new EventoAlmacenNotFoundException("EventoAlmacenConductor no encontrado"));

        eventoAlmacenConductorRepositorio.delete(eventoAlmacenConductor);

        return new Response(
                "Se ha eliminado correctamente el registro " + eventoAlmancenConductorId + ".",
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

    public Boolean conductorInscritoEnEventoAlmacen(int idConductor, int idEventoAlmacen, int idAlmacen) {
        //Busco el evento
        Optional<EventoAlmacen> e = eventoAlmacenRepositorio.findById(idEventoAlmacen);
        //Busco todos los almacenes del evento
        List<EventoAlmacen> almacenesEvento = eventoAlmacenRepositorio.findAllByEventoId(e.get().getEvento().getId());
        //Compruebo uno por uno que no exista uno junto con el conductor
        for (EventoAlmacen eventoAlmacen : almacenesEvento) {
            //Busco el evento almacen de este almacen
            if (eventoAlmacen.getAlmacen().getId().equals(idAlmacen)) {
                //Busco el eventoAlmacenConductor por el id del evento almacen y del conductor
                EventoAlmacenConductor eventoAlmacenConductor = eventoAlmacenConductorRepositorio.findEventoAlmacenConductorByConductorIdAndEventoAlmacenId(idConductor,eventoAlmacen.getId());
                if(eventoAlmacenConductor == null){
                    return false;
                }
            }
        }
        return true;
    }


public Conductor cambiarEstadoConductor(int id) {
    Conductor c = conductorRepositorio.findById(id).get();
    c.setEsActivo(!c.getEsActivo());
    return conductorRepositorio.save(c);
}

/**
 * Método para eliminar un conductor.
 *
 * @param id ID del conductor a eliminar
 * @return Respuesta con el mensaje de eliminación
 */
public void borrarConductor(int id) {
    Conductor c = conductorRepositorio.findById(id).orElseThrow(() -> new ConductorNotFoundException("Conductor no encontrado"));
    c.getVehiculos().remove(c);
    c.getEventoAlmacenConductores().remove(c);







    conductorRepositorio.delete(c);
}


public List<VehiculoResponseDTO> getVehiculosByConductorId(Integer id) {
    List<Vehiculo> vehiculos = vehiculoRepositorio.findVehiculosByConductorId(id);
    return vehiculos.stream().map(VehiculoResponseDTO::VehiculoResponseDTOfromVehiculo).toList();
}

}
