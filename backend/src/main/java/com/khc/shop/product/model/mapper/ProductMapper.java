package com.khc.shop.product.model.mapper;

import com.khc.shop.product.model.ProductDetailDto;
import com.khc.shop.product.model.ProductDto;
import com.khc.shop.product.model.ProductInfoDto;
import com.khc.shop.product.model.ProductWHDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {
    public List<ProductDto> getProductList(Map<String, String> params) throws SQLException;
    public int getProductCount(Map<String, String> params) throws SQLException;
    public void insertProduct(ProductDto productDto) throws SQLException;
    public void insertProductItem(ProductWHDto productWHDto) throws SQLException;
    public List<ProductDetailDto> getProductDetailListByproductId(Map<String, String> params) throws SQLException;
    public int getProductDetailCountByproductId(String productId) throws SQLException;
    public Integer getProductIdbyproductName(String productName) throws SQLException;
    public ProductInfoDto searchProductByCode(String productCode) throws SQLException;
}
