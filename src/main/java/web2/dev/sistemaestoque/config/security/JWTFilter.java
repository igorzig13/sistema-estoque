package web2.dev.sistemaestoque.config.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import web2.dev.sistemaestoque.model.Role;

import java.io.IOException;
import java.util.List;

public class JWTFilter extends OncePerRequestFilter {

    private final SecurityProperties securityProperties;

    JWTFilter(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(JWTCreator.HEADER_AUTHORIZATION);

        if (token == null)
            token = getAuthTokenFromCookie(request);

        if (token != null && !token.isEmpty()) {
            JWTObject tokenObject = JWTCreator.create(token, securityProperties.getPrefix(), securityProperties.getKey());

            List<SimpleGrantedAuthority> authorities = tokenObject
                    .getRoles().stream()
                    .map(Role::getAuthority)
                    .map(SimpleGrantedAuthority::new)
                    .toList();

            UsernamePasswordAuthenticationToken userToken =
                    new UsernamePasswordAuthenticationToken(
                            tokenObject.getSubject(),
                            null,
                            authorities);

            SecurityContextHolder.getContext().setAuthentication(userToken);
        } else {
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }

    private String getAuthTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JWT_TOKEN".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
