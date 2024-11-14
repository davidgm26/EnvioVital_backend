package com.safa.enviovital.servicios;

import com.safa.enviovital.dto.UsuarioRequestDTO;
import com.safa.enviovital.dto.UsuarioResponseDTO;
import com.safa.enviovital.enumerados.Rol;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.excepciones.NotFoundException.UsuarioNotFoundException;
import com.safa.enviovital.modelos.Usuario;
import com.safa.enviovital.repositorios.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepositorio usuarioRepositorio;

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
    public Usuario crearUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        return Usuario.builder()
                .password(usuarioRequestDTO.getPassword())
                .username(usuarioRequestDTO.getUsername())
                .rol(Rol.USUARIO)  // No guarda aquí
                .build();
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

//    public UsuarioResponseDTO guardar(UsuarioRequestDTO requestDTO) {
//        Usuario usuario = new Usuario();
//        usuario.setUsername(requestDTO.getUsername());
//        usuario.setPassword(requestDTO.getPassword());
//        usuario.setRol(requestDTO.getRol());
//        Usuario usuarioGuardado = usuarioRepositorio.save(usuario);
//        return new UsuarioResponseDTO(usuarioGuardado.getId(), usuarioGuardado.getUsername(), usuarioGuardado.getRol());
//    }
//
//    public UsuarioResponseDTO editar(Integer id, UsuarioRequestDTO requestDTO) {
//        Usuario usuario = usuarioRepositorio.findById(id)
//                .orElseThrow(() -> new RuntimeException("El usuario con ID " + id + " no existe"));
//        usuario.setUsername(requestDTO.getUsername());
//        usuario.setPassword(requestDTO.getPassword());
//        usuario.setRol(requestDTO.getRol());
//        Usuario usuarioActualizado = usuarioRepositorio.save(usuario);
//        return new UsuarioResponseDTO(usuarioActualizado.getId(), usuarioActualizado.getUsername(), usuarioActualizado.getRol());
//    }

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
