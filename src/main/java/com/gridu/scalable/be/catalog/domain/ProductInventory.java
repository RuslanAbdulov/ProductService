package com.gridu.scalable.be.catalog.domain;

public class ProductInventory {

    private String id; //uniq_id
    private boolean available;

    public ProductInventory() {
    }

    public ProductInventory(String id, boolean available) {
        this.id = id;
        this.available = available;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
