package br.com.meli.projetointegrador.controller;


import br.com.meli.projetointegrador.model.request.LoginRequest;
import br.com.meli.projetointegrador.model.request.SignupRequest;
import br.com.meli.projetointegrador.repository.UserRepository;
import br.com.meli.projetointegrador.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Classe controladora responsável por lidar com as rotas referentes a autenticação.
 * Possui rotas para autenticacao.
 * @author Arthur Guedes de Souza
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthService user;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return user.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return user.registerUser(signUpRequest);
    }
}