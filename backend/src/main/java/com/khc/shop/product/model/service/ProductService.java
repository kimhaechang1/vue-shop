package com.khc.shop.product.model.service;


import com.khc.shop.product.model.ProductBrandDto;
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
    public ProductResultDto updateProduct(ProductDto productDto) throws Exception;
    public ProductResultDto updateProductItem(ProductWHDto productWHDto) throws Exception;
    public ProductResultDto deleteProduct(int productId) throws Exception;
    public ProductResultDto deleteProductItem(String productCode) throws Exception;
    public ProductResultDto insertBrand(ProductBrandDto productBrandDto) throws Exception;
    public ProductResultDto getBrandList() throws Exception;
    public ProductResultDto updateBrand(ProductBrandDto productBrandDto) throws Exception;
}
