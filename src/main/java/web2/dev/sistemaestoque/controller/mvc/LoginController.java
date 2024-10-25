package web2.dev.sistemaestoque.controller.mvc;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web2.dev.sistemaestoque.model.auth.Login;
import web2.dev.sistemaestoque.model.auth.Session;
import web2.dev.sistemaestoque.service.AuthService;

@Controller
@RequestMapping("/view/login")
public class LoginController {
    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String viewLogin(Model model) {
        model.addAttribute("loginForm", new Login());
        return "login";
    }

    @PostMapping
    public String login(@ModelAttribute Login loginForm, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            Session session = authService.login(loginForm);
            String token = session.getToken();
            Cookie cookie = new Cookie("JWT_TOKEN", token.substring(token.indexOf(" ") + 1));
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);
            return "redirect:/view/product/list";
        } catch (Exception e) {
            String message = (e instanceof UsernameNotFoundException) ? "Invalid username!" : "Invalid credentials!";
            redirectAttributes.addFlashAttribute("errorMessage", message);
            return "redirect:/view/login";
        }
    }

}
