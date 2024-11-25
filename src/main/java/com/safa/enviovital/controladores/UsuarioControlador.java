package com.safa.enviovital.controladores;

import com.safa.enviovital.dto.UsuarioRequestDTO;
import com.safa.enviovital.dto.UsuarioResponseDTO;
import com.safa.enviovital.excepciones.NotFoundException.UsernameAlredyExistsException;
import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.modelos.Usuario;
import com.safa.enviovital.servicios.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioControlador {

    private final UsuarioService usuarioService;

    @GetMapping("/lista")
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Integer id) {
        Usuario usuario = usuarioService.getUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/registrar/admin")
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody UsuarioRequestDTO requestDTO) throws UsernameAlredyExistsException {
        return ResponseEntity.ok(usuarioService.crearUsuarioAdmin(requestDTO));
    }
//
//    @PutMapping("/editar/{id}")
//    public ResponseEntity<UsuarioResponseDTO> editarUsuario(@PathVariable Integer id, @RequestBody UsuarioRequestDTO requestDTO) {
//        return ResponseEntity.ok(usuarioService.editar(id, requestDTO));
//    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Response> eliminarUsuario(@PathVariable Integer id) {
        Response respuesta = usuarioService.eliminar(id);
        return ResponseEntity.status(respuesta.getStatusCode()).body(respuesta);
    }
}
