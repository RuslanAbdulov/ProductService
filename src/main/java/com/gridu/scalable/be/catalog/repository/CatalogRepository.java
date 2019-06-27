package com.gridu.scalable.be.catalog.repository;

import com.gridu.scalable.be.catalog.domain.Product;
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
public class CatalogRepository {

    private final RestTemplate restTemplate;

    public CatalogRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(commandKey = "catalog-by-id", fallbackMethod = "catalogUnavailableById")
    public Optional<Product> findById(String id) {
        ResponseEntity<Product> response =
                restTemplate.exchange("http://catalog-service/api/catalog/products/{id}",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Product>(){},
                        id);

        return Optional.ofNullable(response.getBody());
    }

    @HystrixCommand(commandKey = "catalog-by-sku", fallbackMethod = "catalogUnavailableBySku")
    public List<Product> findBySku(String sku) {
        ResponseEntity<List<Product>> response =
                restTemplate.exchange("http://catalog-service/api/catalog/products?sku={sku}",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Product>>(){},
                        sku);

        return response.getBody();
    }

    @SuppressWarnings("unused")
    private Optional<Product> catalogUnavailableById(String id) {
        throw new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE, "Catalog service unavailable");
    }

    @SuppressWarnings("unused")
    private List<Product> catalogUnavailableBySku(String sku) {
        throw new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE, "Catalog service unavailable");
    }

}
