package com.gridu.scalable.be.catalog.repository;

import com.google.common.collect.ArrayListMultimap;
import com.gridu.scalable.be.catalog.domain.Product;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

@Repository
public class CatalogRepository {

    private Map<String, Product> idKeyMap = new HashMap<>();
    private ArrayListMultimap<String, Product> skuKeyMultimap = ArrayListMultimap.create();

    public Optional<Product> findById(String id) {
        return Optional.ofNullable(idKeyMap.get(id));
    }

    public List<Product> findBySku(String sku) {
        return skuKeyMultimap.get(sku);
    }

    @PostConstruct
    void loadData() {
        File file = new File("./src/main/resources/static/jcpenney_com-ecommerce_sample.csv");
        try (CSVParser csvParser = CSVParser.parse(file, Charset.defaultCharset() ,CSVFormat.EXCEL.withHeader())){
            csvParser.iterator().forEachRemaining(record -> {
                Product product = new Product(
                        record.get("uniq_id"),
                        record.get("sku"),
                        record.get("name_title"),
                        record.get("description"),
                        record.get("list_price"),
                        record.get("sale_price"),
                        record.get("category"),
                        record.get("category_tree"),
                        record.get("average_product_rating"),
                        record.get("product_url"),
                        record.get("product_image_urls"),
                        record.get("brand"),
                        record.get("total_number_reviews"),
                        record.get("Reviews")
                );

                idKeyMap.put(product.getId(), product);
                skuKeyMultimap.put(product.getSku(), product);
            });
        } catch (IOException e) {
            System.out.println("Could not parse product data, stop the application.");
            throw new RuntimeException(e);
        }

    }
}
