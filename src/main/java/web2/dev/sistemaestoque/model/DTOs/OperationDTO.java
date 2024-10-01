package web2.dev.sistemaestoque.model.DTOs;

import lombok.Data;

@Data
public class OperationDTO {
    private Integer quantity;
    private Long productId;
}
