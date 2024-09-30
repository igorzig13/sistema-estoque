package web2.dev.sistemaestoque.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web2.dev.sistemaestoque.model.DTOs.UserRegisterDTO;
import web2.dev.sistemaestoque.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        userService.createUser(userRegisterDTO);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }
}