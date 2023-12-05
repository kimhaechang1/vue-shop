package com.khc.shop.product.model;

import java.util.List;

public class ProductDetailListDto {
    private List<ProductDetailDto> productDetailDtoList;
    private int totalItemCount;
    private int totalPageCount;

    public List<ProductDetailDto> getProductDetailDtoList() {
        return productDetailDtoList;
    }

    public void setProductDetailDtoList(List<ProductDetailDto> productDetailDtoList) {
        this.productDetailDtoList = productDetailDtoList;
    }

    public int getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(int totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    @Override
    public String toString() {
        return "ProductDetailListDto{" +
                "productDetailDtoList=" + productDetailDtoList +
                ", totalItemCount=" + totalItemCount +
                ", totalPageCount=" + totalPageCount +
                '}';
    }
}
