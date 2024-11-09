package com.safa.enviovital.servicios;

import com.safa.enviovital.dto.ConductorRequestDTO;
import com.safa.enviovital.dto.ConductorResponseDTO;
import com.safa.enviovital.dto.UsuarioRequestDTO;
import com.safa.enviovital.dto.UsuarioResponseDTO;
import com.safa.enviovital.enumerados.Rol;
import com.safa.enviovital.excepciones.NotFoundException.AlmacenNotFoundException;
import com.safa.enviovital.excepciones.NotFoundException.ConductorNotFoundException;
import com.safa.enviovital.excepciones.NotFoundException.EventoAlmacenNotFoundException;
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
public class ConductorService {

    @Autowired
    private final ConductorRepositorio conductorRepositorio;

    private final UsuarioRepositorio usuarioRepositorio;

    private final EventoRepository eventoRepository;

    private final EventoAlmacenRepositorio eventoAlmacenRepositorio;

    private final EventoAlmacenConductorRepositorio eventoAlmacenConductorRepositorio;

    private final AlmacenRepositorio almacenRepositorio;

    /**
     * Método para obtener todos los conductores.
     * @return Lista de conductores
     */
    public List<ConductorResponseDTO> getAll() {
        return conductorRepositorio.findAllConductoresWithDTO();
    }

    /**
     * Método para obtener un conductor por su ID.
     * @param id ID del conductor
     * @return ConductorResponseDTO
     */
    public ConductorResponseDTO getConductorPorId(Integer id) {
        return conductorRepositorio.findConductorByIdWithDTO(id)
                .orElseThrow(() -> new ConductorNotFoundException("El conductor con ID " + id + " no existe"));
    }

    /**
     * Método para guardar un nuevo conductor.
     * @param requestDTO Datos del conductor a guardar
     * @return ConductorResponseDTO con los datos del conductor guardado
     */
    public ConductorResponseDTO guardar(ConductorRequestDTO requestDTO) {
        // Creamos un nuevo Usuario basado en el UsuarioRequestDTO incluido en ConductorRequestDTO
        UsuarioRequestDTO usuarioDTO = requestDTO.getUsuario();
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setRol(Rol.CONDUCTOR); // Asignamos automáticamente el rol de CONDUCTOR

        // Guardamos el usuario primero
        Usuario savedUsuario = usuarioRepositorio.save(usuario);

        // Creamos el Conductor asociado al usuario creado
        Conductor conductor = new Conductor();
        conductor.setNombre(requestDTO.getNombre());
        conductor.setApellidos(requestDTO.getApellidos());
        conductor.setDni(requestDTO.getDni());
        conductor.setDireccion(requestDTO.getDireccion());
        conductor.setTelefono(requestDTO.getTelefono());
        conductor.setFechaNacimiento(requestDTO.getFechaNacimiento());
        conductor.setEmail(requestDTO.getEmail());
        conductor.setUsuario(savedUsuario); // Asignamos el usuario recién creado

        // Guardamos el conductor
        Conductor savedConductor = conductorRepositorio.save(conductor);

        // Retornamos el ConductorResponseDTO con los datos guardados
        return new ConductorResponseDTO(savedConductor.getId(), savedConductor.getNombre(), savedConductor.getApellidos(),
                savedConductor.getDni(), savedConductor.getDireccion(), savedConductor.getTelefono(),
                savedConductor.getFechaNacimiento(), savedConductor.getEmail(), savedConductor.getUsuario().getId());
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

        Conductor updatedConductor = conductorRepositorio.save(conductor);

        return new ConductorResponseDTO(
                updatedConductor.getId(), updatedConductor.getNombre(),
                updatedConductor.getApellidos(), updatedConductor.getDni(), updatedConductor.getDireccion(),
                updatedConductor.getTelefono(), updatedConductor.getFechaNacimiento(), updatedConductor.getEmail(),
                updatedConductor.getUsuario().getId()
        );
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
        // Obtener el EventoAlmacen de la base de datos
        EventoAlmacen eventoAlmacen = eventoAlmacenRepositorio.findById(eventoAlmacenId)
                .orElseThrow(() -> new EventoAlmacenNotFoundException("EventoAlmacen no encontrado"));

        // Obtener el Conductor de la base de datos
        Conductor conductor = conductorRepositorio.findById(conductorId)
                .orElseThrow(() -> new ConductorNotFoundException("Conductor no encontrado"));

        // Verificar si ya existe la relación entre EventoAlmacen y Conductor
        Optional<EventoAlmacenConductor> existingEventoAlmacenConductor = eventoAlmacenConductorRepositorio.findByEventoAlmacenIdAndConductorId(eventoAlmacenId, conductorId);
        if (existingEventoAlmacenConductor.isPresent()) {
            // Si ya existe la relación, retornar un mensaje de error
            Response response = new Response(
                    "El conductor con ID " + conductorId + " ya está registrado en el EventoAlmacen con ID " + eventoAlmacenId + ".",
                    HttpStatus.BAD_REQUEST.value(),
                    LocalDateTime.now()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Crear la relación entre EventoAlmacen y Conductor
        EventoAlmacenConductor eventoAlmacenConductor = new EventoAlmacenConductor();
        eventoAlmacenConductor.setEventoAlmacen(eventoAlmacen);
        eventoAlmacenConductor.setConductor(conductor);

        // Guardar la relación en la base de datos
        eventoAlmacenConductorRepositorio.save(eventoAlmacenConductor);

        // Preparar la respuesta
        Response response = new Response(
                "El conductor con ID " + conductorId + " ha sido registrado exitosamente en el EventoAlmacen con ID " + eventoAlmacenId + ".",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }

}
