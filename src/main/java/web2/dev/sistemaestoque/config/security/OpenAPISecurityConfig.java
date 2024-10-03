package web2.dev.sistemaestoque.config.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info =@Info(
                title = "API de controle de estoques",
                contact = @Contact(
                        name = "Igor Marques", url = "https://github.com/igorzig13"
                ),
                description = "API criada com base nas especificações dadas em uma atividade da disciplina \\\"Desenvolvimento de Sistemas Web II\\\", ministrada pelo professor Itamir Filho."
        )
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenAPISecurityConfig {}
