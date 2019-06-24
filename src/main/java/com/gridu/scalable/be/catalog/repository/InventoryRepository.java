package com.gridu.scalable.be.catalog.repository;

import com.gridu.scalable.be.catalog.domain.ProductInventory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Repository
public class InventoryRepository {

    private final RestTemplate restTemplate;

    public InventoryRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ProductInventory> findByIds(List<String> ids) {

        //TODO
        return new ArrayList<>();
    }


}
