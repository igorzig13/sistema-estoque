package web2.dev.sistemaestoque.service;

import org.springframework.stereotype.Service;
import web2.dev.sistemaestoque.model.DTOs.OperationHistoryDTO;
import web2.dev.sistemaestoque.model.Operation;
import web2.dev.sistemaestoque.repository.OperationRepository;
import web2.dev.sistemaestoque.repository.StoreRepository;

import java.util.List;

@Service
public class OperationService {
    private final OperationRepository operationRepository;

    public OperationService(OperationRepository operationRepository, StoreRepository storeRepository) {
        this.operationRepository = operationRepository;
    }

    public List<Operation> getHistoryByDateTimeAndStore(OperationHistoryDTO dto) {
        return operationRepository.findByDateTimeBetweenAndStore_Id(dto.getBegin(), dto.getEnd(), dto.getStoreId());
    }
}
