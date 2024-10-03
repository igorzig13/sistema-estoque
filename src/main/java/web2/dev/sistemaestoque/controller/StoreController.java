package web2.dev.sistemaestoque.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web2.dev.sistemaestoque.model.DTOs.OperationHistoryDTO;
import web2.dev.sistemaestoque.model.DTOs.StoreRegisterDTO;
import web2.dev.sistemaestoque.model.Operation;
import web2.dev.sistemaestoque.service.OperationService;
import web2.dev.sistemaestoque.service.StoreService;

import java.util.List;

@RestController
@RequestMapping("/stores")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Store", description = "Here you can find store-related operations. Manager or higher role is required.")
public class StoreController {
    private final StoreService storeService;
    private final OperationService operationService;

    public StoreController(StoreService storeService, OperationService operationService) {
        this.storeService = storeService;
        this.operationService = operationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody StoreRegisterDTO storeRegisterDTO) {
        storeService.create(storeRegisterDTO);
        return ResponseEntity.ok("Store registered successfully");
    }

    @GetMapping("/history")
    public List<Operation> getHistory(@RequestBody OperationHistoryDTO operationHistoryDTO) {
        return operationService.getHistoryByDateTimeAndStore(operationHistoryDTO);
    }
}
