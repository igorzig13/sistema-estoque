package web2.dev.sistemaestoque.model.DTOs;

import lombok.Data;
import web2.dev.sistemaestoque.model.Store;

@Data
public class StoreSearchDTO {
    private Long id;
    private String name;

    public static StoreSearchDTO from(Store store) {
       StoreSearchDTO dto = new StoreSearchDTO();
       dto.setId(store.getId());
       dto.setName(store.getName());

       return dto;
    }
}
