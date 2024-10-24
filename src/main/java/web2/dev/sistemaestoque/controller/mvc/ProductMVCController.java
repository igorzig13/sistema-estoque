package web2.dev.sistemaestoque.controller.mvc;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web2.dev.sistemaestoque.model.DTOs.ProductRegisterDTO;

@Controller
@RequestMapping("/view/product")
public class ProductMVCController {

    @GetMapping
    public String list(Model model) {
        // TODO: LOGIC TO SHOW LIST OF REGISTERED PRODUCTS
        return "productList";
    }

    @GetMapping("/form")
    public String viewForm(Model model) {
        model.addAttribute("product", new ProductRegisterDTO());
        return "productForm";
    }

    @PostMapping("/form")
    public String processForm(@ModelAttribute("product") ProductRegisterDTO product) {
        return "redirect:/view/product";
    }
}
