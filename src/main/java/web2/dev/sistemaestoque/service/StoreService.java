package web2.dev.sistemaestoque.service;

import org.springframework.stereotype.Service;
import web2.dev.sistemaestoque.model.DTOs.StoreRegisterDTO;
import web2.dev.sistemaestoque.model.Store;
import web2.dev.sistemaestoque.repository.StoreRepository;

import java.util.ArrayList;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public void create(StoreRegisterDTO storeRegisterDTO) {
        Store store = new Store();
        store.setName(storeRegisterDTO.getName());
        store.setAddress(storeRegisterDTO.getAddress());
        store.setHistory(new ArrayList<>());
        store.setProducts(new ArrayList<>());
        storeRepository.save(store);
    }
}
