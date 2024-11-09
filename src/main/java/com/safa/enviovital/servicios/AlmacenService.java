package com.safa.enviovital.servicios;

import com.safa.enviovital.dto.AlmacenRequestDTO;
import com.safa.enviovital.dto.AlmacenResponseDTO;
import com.safa.enviovital.dto.UsuarioRequestDTO;
import com.safa.enviovital.enumerados.Rol;
import com.safa.enviovital.excepciones.NotFoundException.AlmacenNotFoundException;
import com.safa.enviovital.excepciones.NotFoundException.EventoNotFoundException;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.modelos.*;
import com.safa.enviovital.repositorios.*;
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
public class AlmacenService {

    @Autowired
    private final AlmacenRepositorio almacenRepositorio;
    private final ProvinciaRepositorio provinciaRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final EventoRepository eventoRepositorio;
    private final EventoAlmacenRepositorio eventoAlmacenRepositorio;

    /**
     * Método para obtener todos los almacenes.
     * @return Lista de AlmacenResponseDTO
     */
    public List<AlmacenResponseDTO> getAll() {
        return almacenRepositorio.findAllAlmacenesWithDTO();
    }

    /**
     * Método para obtener un almacén por su ID.
     * @param id ID del almacén
     * @return AlmacenResponseDTO
     */
    public AlmacenResponseDTO getAlmacenPorId(Integer id) {
        return almacenRepositorio.findAlmacenByIdWithDTO(id)
                .orElseThrow(() -> new AlmacenNotFoundException("El almacén con ID " + id + " no existe"));
    }

    /**
     * Método para guardar un nuevo almacén.
     * @param requestDTO Datos del almacén a guardar
     * @return AlmacenResponseDTO con los datos del almacén guardado
     */
    public AlmacenResponseDTO guardar(AlmacenRequestDTO requestDTO) {

        UsuarioRequestDTO usuarioDTO = requestDTO.getUsuario();
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setRol(Rol.ALMACEN);

        Usuario savedUsuario = usuarioRepositorio.save(usuario);
        Provincia provincia = provinciaRepositorio.findById(requestDTO.getIdProvincia())
                .orElseThrow(() -> new AlmacenNotFoundException("Provincia con ID " + requestDTO.getIdProvincia() + " no encontrada"));

        Almacen almacen = new Almacen();
        almacen.setNombre(requestDTO.getNombre());
        almacen.setDescripcion(requestDTO.getDescripcion());
        almacen.setDireccion(requestDTO.getDireccion());
        almacen.setEmail(requestDTO.getEmail());
        almacen.setEsActivo(requestDTO.getEsActivo());
        almacen.setProvincia(provincia);
        almacen.setUsuario(savedUsuario);

        Almacen savedAlmacen = almacenRepositorio.save(almacen);

        return new AlmacenResponseDTO(
                savedAlmacen.getId(),
                savedAlmacen.getNombre(),
                savedAlmacen.getDescripcion(),
                savedAlmacen.getDireccion(),
                savedAlmacen.getEmail(),
                savedAlmacen.getEsActivo(),
                savedAlmacen.getProvincia().getId(),
                savedAlmacen.getUsuario().getId()
        );
    }

    /**
     * Método para editar un almacén existente.
     * @param id ID del almacén a editar
     * @param requestDTO Datos del almacén a editar
     * @return AlmacenResponseDTO con los datos del almacén editado
     */
    public AlmacenResponseDTO editar(Integer id, AlmacenRequestDTO requestDTO) {
        Almacen almacen = almacenRepositorio.findById(id)
                .orElseThrow(() -> new AlmacenNotFoundException("El almacén con ID " + id + " no existe"));

        almacen.setNombre(requestDTO.getNombre());
        almacen.setDescripcion(requestDTO.getDescripcion());
        almacen.setDireccion(requestDTO.getDireccion());
        almacen.setEmail(requestDTO.getEmail());
        almacen.setEsActivo(requestDTO.getEsActivo());

        Provincia provincia = provinciaRepositorio.findById(requestDTO.getIdProvincia())
                .orElseThrow(() -> new AlmacenNotFoundException("Provincia con ID " + requestDTO.getIdProvincia() + " no encontrada"));
        almacen.setProvincia(provincia);

        UsuarioRequestDTO usuarioDTO = requestDTO.getUsuario();
        Usuario usuario = almacen.getUsuario();
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setRol(Rol.ALMACEN);
        usuarioRepositorio.save(usuario);

        almacen.setUsuario(usuario);
        Almacen updatedAlmacen = almacenRepositorio.save(almacen);

        return new AlmacenResponseDTO(
                updatedAlmacen.getId(),
                updatedAlmacen.getNombre(),
                updatedAlmacen.getDescripcion(),
                updatedAlmacen.getDireccion(),
                updatedAlmacen.getEmail(),
                updatedAlmacen.getEsActivo(),
                updatedAlmacen.getProvincia().getId(),
                updatedAlmacen.getUsuario().getId()
        );
    }

    /**
     * Método para eliminar un almacén.
     * @param id ID del almacén a eliminar
     * @return Respuesta con el mensaje de eliminación
     */
    public Response eliminar(Integer id) {
        Almacen almacen = almacenRepositorio.findById(id)
                .orElseThrow(() -> new AlmacenNotFoundException("El almacén con ID " + id + " no existe"));

        almacenRepositorio.delete(almacen);

        return new Response(
                "Almacén con ID " + id + " ha sido eliminado exitosamente",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
    }

    public ResponseEntity<Response> registrarAlmacenEnEvento(Integer idEvento, Integer idAlmacen) {
        Evento evento = eventoRepositorio.findById(idEvento)
                .orElseThrow(() -> new EventoNotFoundException("Evento no encontrado"));
        Almacen almacen = almacenRepositorio.findById(idAlmacen)
                .orElseThrow(() -> new AlmacenNotFoundException("Almacén no encontrado"));

        Optional<EventoAlmacen> existingEventoAlmacen = eventoAlmacenRepositorio.findByEventoAndAlmacen(evento, almacen);
        if (existingEventoAlmacen.isPresent()) {
            Response response = new Response(
                    "El almacén con ID " + idAlmacen + " ya está registrado en el evento con ID " + idEvento + ".",
                    HttpStatus.BAD_REQUEST.value(),
                    LocalDateTime.now()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        EventoAlmacen eventoAlmacen = new EventoAlmacen();
        eventoAlmacen.setEvento(evento);
        eventoAlmacen.setAlmacen(almacen);

        eventoAlmacenRepositorio.save(eventoAlmacen);

        Response response = new Response(
                "El almacén con ID " + idAlmacen + " (" + almacen.getNombre() + ") ha sido registrado exitosamente en el evento con ID " + idEvento + " (" + evento.getNombre() + ").",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }



}
