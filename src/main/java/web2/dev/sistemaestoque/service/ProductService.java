package web2.dev.sistemaestoque.service;

import org.springframework.stereotype.Service;
import web2.dev.sistemaestoque.model.DTOs.ProductRegisterDTO;
import web2.dev.sistemaestoque.model.Product;
import web2.dev.sistemaestoque.model.Stock;
import web2.dev.sistemaestoque.repository.ProductRepository;
import web2.dev.sistemaestoque.repository.StockRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    public ProductService(ProductRepository productRepository, StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }

    public void registerProduct(ProductRegisterDTO productRegisterDTO) {
        Product product = new Product();

        product.setName(productRegisterDTO.getName());
        product.setDescription(productRegisterDTO.getDescription());
        product.setPurchasePrice(productRegisterDTO.getPurchasePrice());
        product.setSalePrice(productRegisterDTO.getSalePrice());
        product.setQuantity(productRegisterDTO.getQuantity());

        Stock stock = stockRepository.findByStore_Id(productRegisterDTO.getStoreId())
                .orElseThrow(() -> new RuntimeException("Stock not found using the supplied store id " + productRegisterDTO.getStoreId()));

        product.setStock(stock);
        stock.getProducts().add(product);
        productRepository.save(product);
    }

    public List<Product> findByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> findByQuantityBetween(int min, int max) {
        return productRepository.findByQuantityBetween(min, max);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> findLowQuantity(int limit) {
        return productRepository.findByQuantityLessThan(limit);
    }
}
