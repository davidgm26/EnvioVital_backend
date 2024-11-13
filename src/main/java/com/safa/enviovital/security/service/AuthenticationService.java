package com.safa.enviovital.security.service;

import com.safa.enviovital.enumerados.Rol;
import com.safa.enviovital.modelos.Usuario;
import com.safa.enviovital.repositorios.UsuarioRepositorio;
import com.safa.enviovital.security.dto.LoginRequest;
import com.safa.enviovital.security.dto.LoginResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    private PasswordEncoder passwordEncoder;


    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        var user = usuarioRepositorio.findTopByUsername(loginRequest.getUsername()).orElseThrow(EntityNotFoundException::new);
        var jwtToken = jwtService.generateToken(user);
        return LoginResponse.builder()
                .username(user.getUsername())
                .token(jwtToken)
                .build();

    }

    public Usuario register(LoginRequest loginRequest){
        Usuario usuario = Usuario.builder()
                .password(passwordEncoder.encode(loginRequest.getPassword()))
                .username(loginRequest.getUsername())
                .rol(Rol.USUARIO)
                .build();

        usuarioRepositorio.save(usuario);

        var token = jwtService.generateToken(usuario);

        return usuario;

    }

}
