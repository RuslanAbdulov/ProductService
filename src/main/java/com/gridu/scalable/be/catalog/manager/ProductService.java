package com.gridu.scalable.be.catalog.manager;

import com.gridu.scalable.be.catalog.domain.Product;
import com.gridu.scalable.be.catalog.repository.CatalogRepository;
import com.gridu.scalable.be.catalog.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final CatalogRepository catalogRepository;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public ProductService(CatalogRepository productRepository, InventoryRepository inventoryRepository) {
        this.catalogRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public Optional<Product> findById(String id) {

        inventoryRepository.findByIds(Collections.singletonList(id));
        return catalogRepository.findById(id);
    }

    public List<Product> findBySku(String sku) {
        inventoryRepository.findByIds(Collections.emptyList());

        return catalogRepository.findBySku(sku);
    }

}
