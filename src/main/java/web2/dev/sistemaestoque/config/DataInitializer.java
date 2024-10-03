package web2.dev.sistemaestoque.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import web2.dev.sistemaestoque.model.Role;
import web2.dev.sistemaestoque.model.User;
import web2.dev.sistemaestoque.repository.RoleRepository;
import web2.dev.sistemaestoque.repository.UserRepository;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        createRoleIfNotExists("ROLE_USER");
        createRoleIfNotExists("ROLE_MANAGER");
        createRoleIfNotExists("ROLE_ADMIN");

        createUserIfNotExists("user", "user@email.com", "user", "ROLE_USER");
        createUserIfNotExists("admin", "admin@email.com", "admin", "ROLE_ADMIN");
        createUserIfNotExists("manager", "manager@email.com", "manager", "ROLE_MANAGER");

    }

    private void createRoleIfNotExists(String roleName) {
        if (roleRepository.findByName(roleName).isEmpty()) {
            Role role = new Role();
            role.setName(roleName);
            roleRepository.save(role);
        }
    }

    private void createUserIfNotExists(String userName, String email, String password, String roleName) {
        if (userRepository.findByEmail(email).isEmpty()) {
            User user = new User();
            user.setUsername(userName);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new RuntimeException("User " + userName + " has not been created. Invalid role name: " + roleName));
            user.setRoles(Set.of(role));
            userRepository.save(user);
        }
    }
}
