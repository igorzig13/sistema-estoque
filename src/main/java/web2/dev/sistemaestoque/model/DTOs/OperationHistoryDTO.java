package web2.dev.sistemaestoque.model.DTOs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OperationHistoryDTO {
    LocalDateTime begin;
    LocalDateTime end;
    Long storeId;
}
