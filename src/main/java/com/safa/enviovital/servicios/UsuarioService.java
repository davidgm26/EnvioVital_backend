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

    private final EmailService emailService;

    public List<UsuarioResponseDTO> getAll() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        return usuarios.stream()
                .map(usuario -> new UsuarioResponseDTO(usuario.getId(), usuario.getUsername(), usuario.getRol()))
                .toList();
    }

    public Usuario getUsuarioPorId(Integer id) {
        return usuarioRepositorio.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("El usuario con ID " + id + " no existe"));
    }
    public Usuario crearUsuarioAdmin(UsuarioRequestDTO usuarioRequestDTO) throws UsernameAlredyExistsException {
        Usuario u = crearUsuario(usuarioRequestDTO);
        u.setRol(Rol.ADMIN);
        return guardarUsuario(u);
    }

    public Usuario getUsuarioPorUsername(String username) {
        return usuarioRepositorio.findTopByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No se ha encontrado ningun usuario con el nombre " +username));
    }
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

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

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
