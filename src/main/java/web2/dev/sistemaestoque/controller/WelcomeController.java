package web2.dev.sistemaestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {
    @GetMapping("/")
    public ModelAndView redirectToHome() {
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/home")
    public String showHome() {
        return "home";
    }
}
