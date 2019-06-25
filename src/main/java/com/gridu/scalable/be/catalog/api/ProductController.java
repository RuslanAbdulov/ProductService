package com.gridu.scalable.be.catalog.api;

import com.gridu.scalable.be.catalog.domain.Product;
import com.gridu.scalable.be.catalog.manager.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@SuppressWarnings("unchecked")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> productById(@PathVariable String id) {
        return productService.findById(id)
                .map(product -> new ResponseEntity(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity("no data", HttpStatus.OK));
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> productsBySku(@RequestParam String sku) {
        return new ResponseEntity(productService.findBySku(sku), HttpStatus.OK);
    }

}
