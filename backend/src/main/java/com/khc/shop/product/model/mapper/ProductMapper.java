package com.khc.shop.product.model.mapper;

import com.khc.shop.product.model.ProductBrandDto;
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
    public List<ProductWHDto> getProductWHListByProductId(Map<String, String> params) throws SQLException;
    public int getProductWHCountByProductId(Map<String, String> params) throws SQLException;
    public Integer getProductIdbyProductName(String productName) throws SQLException;
    public ProductInfoDto searchProductByCode(String productCode) throws SQLException;
    public void updateProduct(ProductDto productDto) throws SQLException;
    public void updateProductItem(ProductWHDto productWHDto) throws SQLException;
    public int getProductCountByProductId(int productId) throws SQLException;
    public void deleteProduct(int productId) throws SQLException;
    public void deleteProductItem(String productCode) throws SQLException;
    public Integer countByBrandName(String brandName) throws SQLException;
    public void insertBrand(ProductBrandDto productBrandDto) throws SQLException;
    public List<ProductBrandDto> getBrandList() throws SQLException;
    public void updateBrand(ProductBrandDto productBrandDto) throws SQLException;
    public String getProductBrandNameByBrandId(String productBrandId) throws SQLException;
    public void deleteBrand(String productBrandId) throws SQLException;

}
