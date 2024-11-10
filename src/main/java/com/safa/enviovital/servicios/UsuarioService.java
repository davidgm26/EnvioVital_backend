package com.safa.enviovital.servicios;


import com.safa.enviovital.modelos.Usuario;
import com.safa.enviovital.repositorios.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepositorio usuarioRepositorio;

    /**
     * Metodo que se encarga de dar una lista de usuarios
     *
     * @return Lista de usuarios
     */

    public List<Usuario> getAll() {
        return usuarioRepositorio.findAll();
    }

    /**
     * Metodo que se encarga de guardar un usuario
     * @param usuario Usuario a guardar
     * @return Usuario guardado
     */

    public Usuario guardar(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    /**
     * Metodo que se encarga de eliminar un usuario
     * @param id Id del usuario a eliminar
     */

    public void eliminar(Integer id) {
        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);

        if (usuario == null) {
            throw new RuntimeException("El usuario con id: " + id + " no existe");
        }
        try {
            usuarioRepositorio.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("El usuario con id: " + id + " no puede ser eliminado");
        }
    }

    /**
     * Metodo que se encarga de actualizar un usuario
     * @param usuario Usuario a actualizar
     * @return Usuario actualizado
     */

    public Usuario actualizar(Usuario usuario) {
        return usuarioRepositorio.save(usuario);

    }

}
