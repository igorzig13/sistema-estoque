package web2.dev.sistemaestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web2.dev.sistemaestoque.model.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
}
