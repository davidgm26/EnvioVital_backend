package com.safa.enviovital.servicios;

import com.safa.enviovital.dto.UsuarioRequestDTO;
import com.safa.enviovital.dto.UsuarioResponseDTO;
import com.safa.enviovital.enumerados.Rol;
import com.safa.enviovital.excepciones.NotFoundException.UsernameAlredyExistsException;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.excepciones.NotFoundException.UsuarioNotFoundException;
import com.safa.enviovital.modelos.Usuario;
import com.safa.enviovital.repositorios.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;


    /**
     * Metodo para buscar todos los usuarios disponibles en la base de datos.
     * @return Lista de todos los usuarios usando el DTO
     */
    public List<UsuarioResponseDTO> getAll() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        return usuarios.stream()
                .map(usuario -> new UsuarioResponseDTO(usuario.getId(), usuario.getUsername(), usuario.getRol()))
                .toList();
    }

    /**
     * Metodo para buscar un usuario por id en base de datos.
     * @param id
     * @return Usuario convertido en DTO
     */
    public Usuario getUsuarioPorId(Integer id) {
        return usuarioRepositorio.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("El usuario con ID " + id + " no existe"));
    }

    /**
     * Metodo para crear un administrador
     * @param usuarioRequestDTO
     * @return
     * @throws UsernameAlredyExistsException
     */
    public Usuario crearUsuarioAdmin(UsuarioRequestDTO usuarioRequestDTO) throws UsernameAlredyExistsException {
        Usuario u = crearUsuario(usuarioRequestDTO);
        u.setRol(Rol.ADMIN);
        return guardarUsuario(u);
    }

    /**
     * Metodo para buscar un usuario por su nombre de usuario en base de datos.
     * @param username
     * @return
     */
    public Usuario getUsuarioPorUsername(String username) {
        return usuarioRepositorio.findTopByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No se ha encontrado ningun usuario con el nombre " +username));
    }

    /**
     * Metodo para crear un usuario normal
     * @param usuarioRequestDTO
     * @return Un nuevo usuaeio.
     * @throws UsernameAlredyExistsException
     */
    public Usuario crearUsuario(UsuarioRequestDTO usuarioRequestDTO) throws UsernameAlredyExistsException {
        if(usuarioRepositorio.findTopByUsername(usuarioRequestDTO.getUsername()).isPresent()) {
            throw new UsernameAlredyExistsException(usuarioRequestDTO.getUsername());
        }
    Usuario u = Usuario.builder()
                .password(passwordEncoder.encode(usuarioRequestDTO.getPassword()))
                .username(usuarioRequestDTO.getUsername())
                .rol(Rol.USUARIO)
                .build();

        return guardarUsuario(u);
    }

    /**
     * Metodo para guardar un usuario en base de datos
     * @param usuario
     * @return
     */
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    /**
     * Metodo para eliminar un usuario buscado por id de la base de datos.
     * @param id
     * @return mensaje de exito
     */
    public Response eliminar(Integer id) {
        Usuario usuario = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("El usuario con ID " + id + " no existe"));

        usuarioRepositorio.delete(usuario);

        return new Response(
                "Usuario con ID " + id + " ha sido eliminado exitosamente",
                HttpStatus.OK.value(),
                LocalDateTime.now()
        );
    }
}
