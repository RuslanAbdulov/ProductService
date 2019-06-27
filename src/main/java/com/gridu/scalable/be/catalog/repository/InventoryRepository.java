package com.gridu.scalable.be.catalog.repository;

import com.gridu.scalable.be.catalog.domain.ProductInventory;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Repository
public class InventoryRepository {

    private final RestTemplate restTemplate;

    public InventoryRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<ProductInventory> findById(String id) {
        return findByIds(Collections.singletonList(id)).stream().findFirst();
    }

    @HystrixCommand(commandKey = "inventory-by-ids", fallbackMethod = "inventoryUnavailable")
    public List<ProductInventory> findByIds(List<String> ids) {
        ResponseEntity<List<ProductInventory>> response =
                restTemplate.exchange("http://inventory-service/api/inventory?uniq_ids={uniq_ids}",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<ProductInventory>>(){},
                        String.join(",", ids));

        return response.getBody();
    }

    @SuppressWarnings("unused")
    private List<ProductInventory> inventoryUnavailable(List<String> ids) {
        throw new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE, "Inventory service unavailable");
    }

}
