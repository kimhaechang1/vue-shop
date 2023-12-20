package com.khc.shop.product.model;

public class ProductBrandDto {
    private String productBrandId;
    private String productBrandName;

    public String getProductBrandId() {
        return productBrandId;
    }

    public void setProductBrandId(String productBrandId) {
        this.productBrandId = productBrandId;
    }

    public String getProductBrandName() {
        return productBrandName;
    }

    public void setProductBrandName(String productBrandName) {
        this.productBrandName = productBrandName;
    }

    @Override
    public String toString() {
        return "ProductBrandDto{" +
                "productBrandId='" + productBrandId + '\'' +
                ", productBrandName='" + productBrandName + '\'' +
                '}';
    }
}
