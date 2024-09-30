package web2.dev.sistemaestoque.config.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.config")
@Getter
@Setter
public class SecurityProperties {
    private String prefix;
    private String key;
    private Long expiration;


}