package web2.dev.sistemaestoque.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "stores")
public @Data class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    private String name;

    private String address;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Product> products;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Operation> history;
}
