package com.khc.shop.product.model;

public class ProductDto {
    //product_id, product_name, product_count, product_size, product_img, product_detail, product_date, product_brand
    private String productId;
    private String productName;
    private String productImg;
    private String productBrand;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productImg='" + productImg + '\'' +
                ", productBrand='" + productBrand + '\'' +
                '}';
    }
}
