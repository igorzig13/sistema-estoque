package web2.dev.sistemaestoque.model.DTOs;

import lombok.Data;
import web2.dev.sistemaestoque.model.Product;

import java.math.BigDecimal;

@Data
public class ProductRegisterDTO {
    private String name;
    private String description;
    private BigDecimal purchasePrice;
    private BigDecimal salePrice;
    private Integer quantity;
    private Long storeId;

    public static ProductRegisterDTO from(Product product) {
        ProductRegisterDTO dto = new ProductRegisterDTO();
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPurchasePrice(product.getPurchasePrice());
        dto.setSalePrice(product.getSalePrice());
        dto.setQuantity(product.getQuantity());
        dto.setStoreId(product.getStore().getId());
        return dto;
    }
}