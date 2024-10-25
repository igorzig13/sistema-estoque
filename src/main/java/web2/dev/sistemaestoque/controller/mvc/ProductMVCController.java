package web2.dev.sistemaestoque.controller.mvc;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web2.dev.sistemaestoque.model.DTOs.ProductRegisterDTO;
import web2.dev.sistemaestoque.service.ProductService;
import web2.dev.sistemaestoque.service.StoreService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/view/product")
@Transactional
public class ProductMVCController {

    private final ProductService productService;
    private final StoreService storeService;

    public ProductMVCController(ProductService productService, StoreService storeService) {
        this.productService = productService;
        this.storeService = storeService;
    }

    @GetMapping("/list")
    public String showListPage(@RequestParam(value = "store_id", required = false) Long storeId, Model model) {
        List<ProductRegisterDTO> productRegisterDTOs = new ArrayList<>();

        if (storeId != null) {
            productRegisterDTOs = productService.findAllByStoreId(storeId);
        }

        model.addAttribute("products", productRegisterDTOs);
        model.addAttribute("storeId", storeId);
        model.addAttribute("stores", storeService.findAllIdAndName());
        return "productList";
    }

    @PostMapping("/list")
    public String listByStore(@ModelAttribute("store_id") Long id) {
        return "redirect:/view/product/list?store_id=" + id;
    }

    @GetMapping("/form")
    public String viewForm(Model model) {
        model.addAttribute("product", new ProductRegisterDTO());
        return "productForm";
    }

    @PostMapping("/form")
    public String processForm(@ModelAttribute("product") ProductRegisterDTO product) {
        productService.registerProduct(product);
        return "redirect:/view/product/list";
    }
}
