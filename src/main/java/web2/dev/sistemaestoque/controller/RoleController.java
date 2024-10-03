package web2.dev.sistemaestoque.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web2.dev.sistemaestoque.model.DTOs.RoleDTO;
import web2.dev.sistemaestoque.service.RoleService;

@RestController
@RequestMapping("/roles")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Role", description = "Operations related to roles. Admin role is required.")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RoleDTO roleDTO) {
        roleService.create(roleDTO);
        return ResponseEntity.ok().body("The new role " + roleDTO.getName() + " has been created");
    }
}
