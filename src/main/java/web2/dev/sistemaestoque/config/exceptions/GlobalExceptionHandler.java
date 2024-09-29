package web2.dev.sistemaestoque.config.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", System.currentTimeMillis());
        body.put("error", ex.getMessage());
        body.put("message", "An error occurred");
        body.put("exception", ex.getClass().getName());
        body.put("path", request.getDescription(false));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler({ExpiredJwtException.class, UnsupportedJwtException.class, SignatureException.class, MalformedJwtException.class})
    public ResponseEntity<Object> handleJwtException(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", System.currentTimeMillis());
        body.put("error", ex.getMessage());
        body.put("message", "An error related to JWT Filter occurred");
        body.put("exception", ex.getClass().getName());
        body.put("path", request.getDescription(false));

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }
}
