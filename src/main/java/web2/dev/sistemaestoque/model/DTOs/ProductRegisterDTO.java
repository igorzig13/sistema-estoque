package web2.dev.sistemaestoque.model.DTOs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRegisterDTO {
    private String name;
    private String description;
    private BigDecimal purchasePrice;
    private BigDecimal salePrice;
    private Integer quantity;
    private Long storeId;
}
