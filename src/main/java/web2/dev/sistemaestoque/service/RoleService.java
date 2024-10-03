package web2.dev.sistemaestoque.service;

import org.springframework.stereotype.Service;
import web2.dev.sistemaestoque.model.DTOs.RoleDTO;
import web2.dev.sistemaestoque.model.Role;
import web2.dev.sistemaestoque.repository.RoleRepository;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void create(RoleDTO roleDTO) {
        String roleName = "ROLE_" + roleDTO.getName();

        if (roleRepository.findByName(roleName).isEmpty()) {
            Role role = new Role(roleName);
            roleRepository.save(role);
        } else {
            throw new RuntimeException("This role name already exists");
        }
    }
}
