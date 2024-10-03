package web2.dev.sistemaestoque.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web2.dev.sistemaestoque.model.DTOs.UserRegisterDTO;
import web2.dev.sistemaestoque.service.UserService;

@RestController
@RequestMapping(value = "/users")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "User", description = "Operations related to users. Admin role is required.")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        userService.createUser(userRegisterDTO);
        return ResponseEntity.ok("User registered successfully!");
    }
}
