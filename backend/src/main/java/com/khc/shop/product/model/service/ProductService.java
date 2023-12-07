package com.khc.shop.product.model.service;


import com.khc.shop.product.model.ProductDto;
import com.khc.shop.product.model.ProductResultDto;
import com.khc.shop.product.model.ProductWHDto;

import java.util.Map;

public interface ProductService {
    public ProductResultDto getProductList(Map<String, String> params) throws Exception;
    public ProductResultDto insertProduct(ProductDto productDto) throws Exception;
    public ProductResultDto getProductDetailList(Map<String, String> params) throws Exception;
    public ProductResultDto insertProductItem(ProductWHDto productWHDto) throws Exception;
    public ProductResultDto searchProductByCode(String productCode) throws Exception;
}
