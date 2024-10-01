package web2.dev.sistemaestoque.service;

import org.springframework.stereotype.Service;
import web2.dev.sistemaestoque.model.DTOs.OperationDTO;
import web2.dev.sistemaestoque.model.Operation;
import web2.dev.sistemaestoque.model.OperationType;
import web2.dev.sistemaestoque.model.Product;
import web2.dev.sistemaestoque.model.Stock;
import web2.dev.sistemaestoque.repository.OperationRepository;
import web2.dev.sistemaestoque.repository.ProductRepository;

import java.time.LocalDateTime;

@Service
public class StockService {

    private final ProductRepository productRepository;
    private final OperationRepository operationRepository;

    public StockService(ProductRepository productRepository, OperationRepository operationRepository) {
        this.productRepository = productRepository;
        this.operationRepository = operationRepository;
    }

    public void buyProduct(OperationDTO operationDTO) {
        Product product = productRepository.findById(operationDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        product.setQuantity(product.getQuantity() + operationDTO.getQuantity());
        productRepository.save(product);

        Stock stock = product.getStock();

        Operation operation = new Operation();
        operation.setProduct(product);
        operation.setStock(stock);
        operation.setDateTime(LocalDateTime.now());
        operation.setOperationType(OperationType.PURCHASE);

        operationRepository.save(operation);
    }

    public void sellProduct(OperationDTO operationDTO) {
        Product product = productRepository.findById(operationDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (operationDTO.getQuantity() > product.getQuantity()) {
            throw new IllegalArgumentException("Quantity exceeds limit in stock for this product");
        }

        product.setQuantity(product.getQuantity() - operationDTO.getQuantity());
        productRepository.save(product);

        Stock stock = product.getStock();

        Operation operation = new Operation();
        operation.setProduct(product);
        operation.setStock(stock);
        operation.setDateTime(LocalDateTime.now());
        operation.setOperationType(OperationType.SALE);

        operationRepository.save(operation);
    }
}
