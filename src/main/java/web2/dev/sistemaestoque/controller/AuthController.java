package web2.dev.sistemaestoque.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web2.dev.sistemaestoque.model.auth.Login;
import web2.dev.sistemaestoque.model.auth.Session;
import web2.dev.sistemaestoque.service.AuthService;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Session login(@RequestBody Login login) {
        return authService.login(login);
    }
}
