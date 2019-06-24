package com.gridu.scalable.be.catalog.repository;

import com.gridu.scalable.be.catalog.domain.ProductInventory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
        ResponseEntity<List<ProductInventory>> response =
                restTemplate.exchange("http://inventory-service/api/inventory?uniq_ids={ids}",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<ProductInventory>>(){},
                        ids);

        return response.getBody();
    }

}
