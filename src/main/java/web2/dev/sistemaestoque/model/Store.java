package web2.dev.sistemaestoque.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "stores")
public @Data class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    private String name;

    private String address;

    @OneToOne(mappedBy = "stock_id")
    private Stock stock;
}
