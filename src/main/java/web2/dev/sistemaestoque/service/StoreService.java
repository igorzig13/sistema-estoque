package web2.dev.sistemaestoque.service;

import org.springframework.stereotype.Service;
import web2.dev.sistemaestoque.model.DTOs.StoreRegisterDTO;
import web2.dev.sistemaestoque.model.DTOs.StoreSearchDTO;
import web2.dev.sistemaestoque.model.Store;
import web2.dev.sistemaestoque.repository.StoreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<StoreSearchDTO> findAllIdAndName() {
        List<Store> stores = storeRepository.findAll();
        return stores.stream().map(StoreSearchDTO::from).toList();
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
