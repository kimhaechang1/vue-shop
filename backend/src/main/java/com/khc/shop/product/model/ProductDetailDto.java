package com.khc.shop.product.model;

public class ProductDetailDto {
    private int productId;
    private String productCode;
    private int productSize;
    private String productDate;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    @Override
    public String toString() {
        return "ProductDetailDto{" +
                "productId=" + productId +
                ", productCode='" + productCode + '\'' +
                ", productSize=" + productSize +
                ", productDate='" + productDate + '\'' +
                '}';
    }
}
