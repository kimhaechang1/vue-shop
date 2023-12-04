package com.khc.shop.product.model.mapper;

import com.khc.shop.product.model.ProductDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {
    public List<ProductDto> getProductList(Map<String, String> params) throws SQLException;
    public int getProductCount(Map<String, String> params) throws SQLException;
}
