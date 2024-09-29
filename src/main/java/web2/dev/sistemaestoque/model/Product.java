package web2.dev.sistemaestoque.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
public @Data class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;

    @Lob
    private String description;

    @Column(precision = 13, scale = 2)
    private BigDecimal purchasePrice;

    @Column(precision = 13, scale = 2)
    private BigDecimal salePrice;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;
}
