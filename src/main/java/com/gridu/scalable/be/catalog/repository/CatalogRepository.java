package com.gridu.scalable.be.catalog.repository;

import com.gridu.scalable.be.catalog.domain.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Repository
public class CatalogRepository {

    private final RestTemplate restTemplate;

    public CatalogRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<Product> findById(String id) {
        ResponseEntity<Product> response =
                restTemplate.exchange("http://catalog-service/api/catalog/products/{id}",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Product>(){},
                        id);

        return Optional.ofNullable(response.getBody());
    }

    public List<Product> findBySku(String sku) {
        ResponseEntity<List<Product>> response =
                restTemplate.exchange("http://catalog-service/api/catalog/products?sku={sku}",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Product>>(){},
                        sku);

        return response.getBody();
    }

}
