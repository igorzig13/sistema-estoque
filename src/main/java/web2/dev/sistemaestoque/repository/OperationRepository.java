package web2.dev.sistemaestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web2.dev.sistemaestoque.model.Operation;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByDateTimeBetweenAndStore_Id(LocalDateTime begin, LocalDateTime end, Long storeId);
}
