package web2.dev.sistemaestoque.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web2.dev.sistemaestoque.model.DTOs.ProductRegisterDTO;
import web2.dev.sistemaestoque.model.Product;
import web2.dev.sistemaestoque.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Product", description = "All operations related to products. Manager or higher role is required.")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody ProductRegisterDTO productRegisterDTO) {
        productService.registerProduct(productRegisterDTO);
        return ResponseEntity.ok("Product registered successfully!");
    }

    @GetMapping("/by-name")
    public List<Product> findByName(@RequestParam String name) {
        return productService.findByName(name);
    }

    @GetMapping("/by-quantity")
    public List<Product> findByQuantity(@RequestParam int min, @RequestParam int max) {
        return productService.findByQuantityBetween(min, max);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/low-stock")
    public List<Product> findLowQuantity(@RequestParam int limit) {
        return productService.findLowQuantity(limit);
    }
}
