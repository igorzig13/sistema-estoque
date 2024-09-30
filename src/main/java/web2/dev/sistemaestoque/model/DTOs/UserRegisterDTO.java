package web2.dev.sistemaestoque.model.DTOs;

import lombok.Data;

import java.util.Set;

@Data
public class UserRegisterDTO {
    private String username;
    private String password;
    private String email;
    private Set<String> roleNames;
}
