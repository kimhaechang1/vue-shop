package com.khc.shop.product.model;

public class ProductFromClientDto {
    private String productName;
    private String productImg;
    private String productBrand;
    private String productDescription;
    private String productCode;
    private int productSize;

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

    public String getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getProductSize() {
        return productSize;
    }

    public void setProductSize(int productSize) {
        this.productSize = productSize;
    }

    @Override
    public String toString() {
        return "ProductFromClientDto{" +
                "productName='" + productName + '\'' +
                ", productImg='" + productImg + '\'' +
                ", productBrand='" + productBrand + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productSize=" + productSize +
                '}';
    }
}
