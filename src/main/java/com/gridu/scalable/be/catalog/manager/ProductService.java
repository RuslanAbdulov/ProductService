package com.gridu.scalable.be.catalog.manager;

import com.gridu.scalable.be.catalog.domain.Product;
import com.gridu.scalable.be.catalog.domain.ProductInventory;
import com.gridu.scalable.be.catalog.repository.CatalogRepository;
import com.gridu.scalable.be.catalog.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        boolean productAvailable = inventoryRepository.findById(id)
                .map(ProductInventory::isAvailable)
                .orElse(false);

        return productAvailable ? catalogRepository.findById(id) : Optional.empty();
    }

    public List<Product> findBySku(String sku) {
        List<Product> products = catalogRepository.findBySku(sku);

        Map<String, Boolean> availability =
                inventoryRepository.findByIds(
                        products.stream().map(Product::getId).collect(Collectors.toList()))
                        .stream()
                        .collect(Collectors.toMap(ProductInventory::getId, ProductInventory::isAvailable));

        return products.stream()
                .filter(product -> availability.get(product.getId()))
                .collect(Collectors.toList());
    }

}