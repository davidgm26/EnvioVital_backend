package com.safa.enviovital.servicios;

import com.safa.enviovital.dto.*;
import com.safa.enviovital.enumerados.Rol;
import com.safa.enviovital.excepciones.NotFoundException.*;
import com.safa.enviovital.excepciones.NotFoundException.AlmacenNotFoundException;
import com.safa.enviovital.excepciones.NotFoundException.ConductorNotFoundException;
import com.safa.enviovital.excepciones.NotFoundException.EventoAlmacenNotFoundException;
import com.safa.enviovital.excepciones.NotFoundException.UsernameAlredyExistsException;
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
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlmacenService {

    @Autowired
    private AlmacenRepositorio almacenRepositorio;
    @Autowired
    private ProvinciaService provinciaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private  EventoAlmacenConductorRepositorio eventoAlmacenConductorRepositorio;
    @Autowired
    private  EventoAlmacenRepositorio eventoAlmacenRepositorio;
    @Autowired
    private EventoService eventoService;
    @Autowired
    private EmailService emailService;

    /**
     * Método para obtener todos los almacenes.
     * 
     * @return Lista de AlmacenResponseDTO
     */
    public List<AlmacenResponseDTO> getAll() {
        List<Almacen> almacenes = almacenRepositorio.findAll();
        return almacenes.stream().map(AlmacenResponseDTO::AlmacenResponseDtoFromAlmacen).collect(Collectors.toList());
    }

    /**
     * Método para obtener un almacén por su ID.
     * 
     * @param id ID del almacén
     * @return AlmacenResponseDTO
     */
    public Almacen getAlmacenPorId(Integer id) {
        return almacenRepositorio.findAlmacenById(id)
                .orElseThrow(() -> new AlmacenNotFoundException(id));
    }

    public AlmacenResponseDTO getAlmacenByUsuarioId(Integer idUsuario) {
        Almacen a = almacenRepositorio.findAlmacenByUsuarioId(idUsuario)
                .orElseThrow(() -> new AlmacenNotFoundException(idUsuario));

        return AlmacenResponseDTO.AlmacenResponseDtoFromAlmacen(a);
    }

    /**
     * Método para guardar un nuevo almacén.
     * 
     * @param requestDTO Datos del almacén a guardar
     * @return AlmacenResponseDTO con los datos del almacén guardado
     */

    @Transactional
    public Almacen guardar(AlmacenRequestDTO requestDTO) throws AlmacenNameAlredyExistsException, UsernameAlredyExistsException {
        Provincia provincia = provinciaService.getProvinciaById(requestDTO.getIdProvincia());
        Usuario u = usuarioService.crearUsuario(requestDTO.getUsuario());
        Almacen almacen = AlmacenRequestDTO.AlmacenRequestDtoToAlmacen(requestDTO,provincia);
        u.setRol(Rol.ALMACEN);
        almacen.setUsuario(u);
        almacen.setEsActivo(true);
        usuarioService.guardarUsuario(u);
        emailService.sendRegistrationEmail(almacen.getEmail(), almacen.getNombre());
        return guardarAlmacen(almacen);
    }

    /**
     * Método para editar un almacén existente.
     * 
     * @param id         ID del almacén a editar
     * @param requestDTO Datos del almacén a editar
     * @return AlmacenResponseDTO con los datos del almacén editado
     */
    public AlmacenResponseDTO editar(Integer id, AlmacenEditarDTO requestDTO) {

        Almacen almacen = getAlmacenPorId(id);
        almacen.setNombre(requestDTO.getNombre());
        almacen.setDescripcion(requestDTO.getDescripcion());
        almacen.setDireccion(requestDTO.getDireccion());
        almacen.setEmail(requestDTO.getEmail());
        almacen.setProvincia(provinciaService.getProvinciaById(requestDTO.getIdProvincia()));
        almacenRepositorio.save(almacen);

        return AlmacenResponseDTO.AlmacenResponseDtoFromAlmacen(almacen);
    }

    /**
     * Método para eliminar un almacén.
     * 
     * @param id ID del almacén a eliminar
     * @return Respuesta con el mensaje de eliminación
     */
    public Response eliminar(Integer id) {
        Almacen almacen = getAlmacenPorId(id);
        ;
        almacenRepositorio.delete(almacen);
        return new Response(
                "Almacén con ID " + id + " ha sido eliminado exitosamente",
                HttpStatus.OK.value(),
                LocalDateTime.now());
    }

    public ResponseEntity<Response> registrarAlmacenEnEvento(Integer idEvento, Integer idAlmacen) {
        Evento evento = eventoService.getEventoById(idEvento);

        Almacen almacen = almacenRepositorio.findById(idAlmacen)
                .orElseThrow(() -> new AlmacenNotFoundException(idAlmacen));

        Optional<EventoAlmacen> existingEventoAlmacen = eventoAlmacenRepositorio.findByEventoAndAlmacen(evento,
                almacen);
        if (existingEventoAlmacen.isPresent()) {
            Response response = new Response(
                    "El almacén con ID " + idAlmacen + " ya está registrado en el evento con ID " + idEvento + ".",
                    HttpStatus.BAD_REQUEST.value(),
                    LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        EventoAlmacen eventoAlmacen = new EventoAlmacen();
        eventoAlmacen.setEvento(evento);
        eventoAlmacen.setAlmacen(almacen);

        eventoAlmacenRepositorio.save(eventoAlmacen);

        Response response = new Response(
                "El almacén con ID " + idAlmacen + " (" + almacen.getNombre()
                        + ") ha sido registrado exitosamente en el evento con ID " + idEvento + " ("
                        + evento.getNombre() + ").",
                HttpStatus.OK.value(),
                LocalDateTime.now());

        return ResponseEntity.ok(response);
    }

    /**
     *  Metodo que se usa solamente para guardar un almacen
     *  en la base de datos
     * @param a, recibe un almacen
     * @return el almacen que ha recibido por parametros guardaddo en base de datos
     */
    public Almacen guardarAlmacen(Almacen a) {
        return almacenRepositorio.save(a);
    }

    /**
     * Método para obtener los almacenes registrados en un evento.
     * 
     * @param idEvento ID del evento
     * @return Lista de AlmacenResponseDTO
     */

    public List<EventoAlmacenDtoResponse> obtenerEventoAlmacenPorEvento(Integer idEvento) {
        // Llamar al repositorio para obtener la lista de relaciones entre eventos y
        // almacenes
        List<EventoAlmacen> lista = eventoAlmacenRepositorio.findEventoAlmacenByEventoIdAndEsActivo(idEvento);
        return lista.stream().map(EventoAlmacenDtoResponse::toDto).toList();
    }


    /**
     * Método para obtener los eventos en los que esta registrado un almacen.
     *
     * @param idAlmacen ID del evento
     * @return Lista de AlmacenResponseDTO
     */
    public List<ListaEventosByAlmacenDTO> obtenerEventoAlmacenPorAlmacen(Integer idAlmacen) {
        List<EventoAlmacen> lista = eventoAlmacenRepositorio.findEventoAlmacenByAlmacenId(idAlmacen);
        return lista.stream().map(ListaEventosByAlmacenDTO::toDto).toList();
    }

    /**
     *  Metodo para eliminar inscripicion de un almacen en un evento
     * @param EventoAlmacenId
     * @return Respuesta con el mensaje de eliminación
     */
    public Response eliminarRegistroAlmacenEnEvento(Integer EventoAlmacenId) {
        EventoAlmacen eventoAlmacen = eventoAlmacenRepositorio.findById(EventoAlmacenId)
                .orElseThrow(() -> new EventoAlmacenNotFoundException("EventoAlmacen no encontrado"));

        eventoAlmacenRepositorio.delete(eventoAlmacen);

        return new Response(
                "Se ha eliminado correctamente el registro " + EventoAlmacenId + ".",
                HttpStatus.OK.value(),
                LocalDateTime.now());
    }

    /**
     *  Metodo para obtener todos los conductores inscritos en un almacen.
     * @param almacenId
     * @return
     */
    public List<ConductorResponseDTO> obtenerConductoresPorAlmacen(Integer almacenId) {
        List<EventoAlmacenConductor> eventoAlmacenConductores = eventoAlmacenConductorRepositorio.findByEventoAlmacenId_AlmacenId(almacenId);
        Set<Conductor> uniqueConductors = eventoAlmacenConductores.stream()
                .map(EventoAlmacenConductor::getConductor)
                .collect(Collectors.toSet());
        return uniqueConductors.stream()
                .map(ConductorResponseDTO::ConductorResponseDtoFromConductor)
                .collect(Collectors.toList());
    }

    /**
     *  Metodo para cambiar el estado del almacen,
     * @param id, id del almacen
     * @return
     */
    public Almacen changeAlmacenState(int id) {
        Almacen a = getAlmacenPorId(id);
        a.setEsActivo(!a.getEsActivo());
        return guardarAlmacen(a);
    }

    // public AlmacenResponseDTO

}
