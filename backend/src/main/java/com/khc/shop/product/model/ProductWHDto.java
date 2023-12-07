package com.khc.shop.product.model;

public class ProductWHDto {
    private String productCode;
    private Integer productId;
    private Integer productSize;
    private String productDate;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductSize() {
        return productSize;
    }

    public void setProductSize(Integer productSize) {
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
        return "ProductWHDto{" +
                "productCode='" + productCode + '\'' +
                ", productId=" + productId +
                ", productSize=" + productSize +
                ", productDate='" + productDate + '\'' +
                '}';
    }
}
