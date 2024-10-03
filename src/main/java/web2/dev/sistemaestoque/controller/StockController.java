package web2.dev.sistemaestoque.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web2.dev.sistemaestoque.model.DTOs.OperationDTO;
import web2.dev.sistemaestoque.service.StockService;

@RestController("/stock")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Stock", description = "Stock control operations: Purchase and sale. Manager or higher role is required.")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("/products/buy")
    public ResponseEntity<String> buyProduct(@RequestBody OperationDTO operationDTO) {
        stockService.buyProduct(operationDTO);
        return ResponseEntity.ok().body("Buy operation successful");
    }

    @PostMapping("/products/sell")
    public ResponseEntity<String> sellProduct(@RequestBody OperationDTO operationDTO) {
        stockService.sellProduct(operationDTO);
        return ResponseEntity.ok().body("Sell operation successful");
    }
}
