package com.gridu.scalable.be.catalog.domain;

public class Product {

    private String id; //uniq_id
    private String sku; //sku
    private String nameTitle; //name_title
    private String description; //description
    private String listPrice; //list_price
    private String salePrice; //sale_price
    private String category; //category
    private String categoryTree; //category_tree
    private String avgProductRating; //average_product_rating
    private String productUrl; //product_url
    private String productImageUrls; //product_image_urls
    private String brand; //brand
    private String totalNumberReviews; //total_number_reviews
    private String reviews; //Reviews

    public Product() {
    }

    public Product(String id, String sku, String nameTitle, String description, String listPrice, String salePrice,
                   String category, String categoryTree, String avgProductRating, String productUrl,
                   String productImageUrls, String brand, String totalNumberReviews, String reviews) {
        this.id = id;
        this.sku = sku;
        this.nameTitle = nameTitle;
        this.description = description;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.category = category;
        this.categoryTree = categoryTree;
        this.avgProductRating = avgProductRating;
        this.productUrl = productUrl;
        this.productImageUrls = productImageUrls;
        this.brand = brand;
        this.totalNumberReviews = totalNumberReviews;
        this.reviews = reviews;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNameTitle() {
        return nameTitle;
    }

    public void setNameTitle(String nameTitle) {
        this.nameTitle = nameTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getListPrice() {
        return listPrice;
    }

    public void setListPrice(String listPrice) {
        this.listPrice = listPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryTree() {
        return categoryTree;
    }

    public void setCategoryTree(String categoryTree) {
        this.categoryTree = categoryTree;
    }

    public String getAvgProductRating() {
        return avgProductRating;
    }

    public void setAvgProductRating(String avgProductRating) {
        this.avgProductRating = avgProductRating;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductImageUrls() {
        return productImageUrls;
    }

    public void setProductImageUrls(String productImageUrls) {
        this.productImageUrls = productImageUrls;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTotalNumberReviews() {
        return totalNumberReviews;
    }

    public void setTotalNumberReviews(String totalNumberReviews) {
        this.totalNumberReviews = totalNumberReviews;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }
}
