package com.safa.enviovital.security.service;

import com.safa.enviovital.dto.UsuarioRequestDTO;
import com.safa.enviovital.excepciones.NotFoundException.UsernameAlredyExistsException;
import com.safa.enviovital.excepciones.PasswordNotCorrectException;
import com.safa.enviovital.modelos.Usuario;
import com.safa.enviovital.repositorios.UsuarioRepositorio;
import com.safa.enviovital.security.dto.ChangePasswordRequest;
import com.safa.enviovital.security.dto.LoginRequest;
import com.safa.enviovital.security.dto.LoginResponse;
import com.safa.enviovital.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public LoginResponse login(LoginRequest loginRequest) {
        var nombreUsuario = usuarioService.getUsuarioPorUsername(loginRequest.getUsername());

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new PasswordNotCorrectException();
        }

        var user = usuarioService.getUsuarioPorUsername(loginRequest.getUsername());
        var jwtToken = jwtService.generateToken(user);
        return LoginResponse.builder()
                .username(user.getUsername())
                .id(user.getId())
                .token(jwtToken)
                .rol(nombreUsuario.getRol().toString())
                .build();

    }

    public Usuario register(UsuarioRequestDTO usuarioRequestDTO) throws UsernameAlredyExistsException {
    return usuarioService.crearUsuario(usuarioRequestDTO);


    }
    public boolean changePassword(Integer usuarioId, ChangePasswordRequest changePasswordRequest) {


        Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(usuarioId);

        if (usuarioOptional.isEmpty()) {
            return false;
        }

        Usuario usuario = usuarioOptional.get();


        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), usuario.getPassword())) {
            return false; // Si las contraseñas no coinciden, retornamos false
        }


        String newPasswordEncoded = passwordEncoder.encode(changePasswordRequest.getNewPassword());

        usuario.setPassword(newPasswordEncoded);


        usuarioRepositorio.save(usuario);

        return true;
    }


}