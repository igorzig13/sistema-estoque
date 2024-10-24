package web2.dev.sistemaestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web2.dev.sistemaestoque.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByQuantityBetween(int min, int max);

    List<Product> findByQuantityLessThan(int limit);

    List<Product> findAllByStoreId(Long storeId);
}
