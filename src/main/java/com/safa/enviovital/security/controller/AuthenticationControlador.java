package com.safa.enviovital.security.controller;

import com.safa.enviovital.excepciones.Response;
import com.safa.enviovital.modelos.Usuario;
import com.safa.enviovital.security.dto.ChangePasswordRequest;
import com.safa.enviovital.security.dto.LoginRequest;
import com.safa.enviovital.security.dto.LoginResponse;
import com.safa.enviovital.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthenticationControlador {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.register(loginRequest));
    }

    @PostMapping("/change-password/{usuarioId}")
    public ResponseEntity<Response> changePassword(
            @PathVariable Integer usuarioId,
            @RequestBody ChangePasswordRequest changePasswordRequest) {

        boolean isUpdated = authenticationService.changePassword(usuarioId, changePasswordRequest);

        if (isUpdated) {
            Response response = new Response("Contraseña cambiada correctamente", HttpStatus.OK.value(), LocalDateTime.now());
            return ResponseEntity.ok(response);  // Respuesta con código 200 OK
        } else {
            Response response = new Response("Fallo al cambiar la contraseña", HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);  // Respuesta con código 400 BAD_REQUEST
        }
    }
}