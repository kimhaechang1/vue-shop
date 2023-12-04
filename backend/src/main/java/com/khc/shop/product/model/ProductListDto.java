package com.khc.shop.product.model;

import java.util.List;

public class ProductListDto {
    private List<ProductDto> productList;
    private int totalPageCount;
    private int totalItemCount;

    public List<ProductDto> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductDto> productList) {
        this.productList = productList;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(int totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    @Override
    public String toString() {
        return "ProductListDto{" +
                "productList=" + productList +
                ", totalPageCount=" + totalPageCount +
                ", totalItemCount=" + totalItemCount +
                '}';
    }
}
