package web2.dev.sistemaestoque.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "stocks")
public @Data class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long id;

    @OneToMany(mappedBy = "stock", fetch = FetchType.EAGER)
    private List<Product> products;

    @OneToMany(mappedBy = "stock", fetch = FetchType.LAZY)
    private List<Operation> history;

    @OneToOne(mappedBy = "stock")
    private Store store;
}
