package com.khc.shop.product.model.service;


import com.khc.shop.product.model.ProductDetailDto;
import com.khc.shop.product.model.ProductFromClientDto;
import com.khc.shop.product.model.ProductResultDto;

import java.util.Map;

public interface ProductService {
    public ProductResultDto getProductList(Map<String, String> params) throws Exception;

    public ProductResultDto productInsert(ProductFromClientDto productFromClientDto) throws Exception;

    public ProductResultDto getProductDetailList(Map<String, String> params) throws Exception;
}
