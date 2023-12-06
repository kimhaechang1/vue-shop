package com.khc.shop.product.model;

public class ProductInfoDto {

    //product_id, product_name, product_img, product_brand, product_description, product_code, product_size, product_date

    private Integer productId;
    private String productName;
    private String productImg;
    private String productBrand;
    private String productDescription;
    private String productCode;
    private String productSize;
    private String productDate;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
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

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
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
        return "ProductInfoDto{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productImg='" + productImg + '\'' +
                ", productBrand='" + productBrand + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productSize='" + productSize + '\'' +
                ", productDate='" + productDate + '\'' +
                '}';
    }
}
