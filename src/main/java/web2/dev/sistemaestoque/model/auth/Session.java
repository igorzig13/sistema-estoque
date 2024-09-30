package web2.dev.sistemaestoque.model.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Session {
    private String login;
    private String token;
}
