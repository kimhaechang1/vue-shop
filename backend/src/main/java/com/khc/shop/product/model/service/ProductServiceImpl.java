package com.khc.shop.product.model.service;

import com.khc.shop.product.exception.DuplicatedProductException;
import com.khc.shop.product.model.*;
import com.khc.shop.product.model.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository

public class ProductServiceImpl implements ProductService{

    private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private ProductMapper mapper;

    private final PlatformTransactionManager transactionManager;

    public ProductServiceImpl(ProductMapper mapper, PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
        this.mapper = mapper;
    }

    @Override
    public ProductResultDto getProductList(Map<String, String> params) throws Exception {
        String pgnoParam = params.get("pgno");
        String sppParam = params.get("spp");
        int pgno = 1;
        int spp = 9;
        if(pgnoParam != null && !"".equals(pgnoParam)) pgno = Integer.parseInt(pgnoParam);
        if(sppParam != null && !"".equals(sppParam)) spp = Integer.parseInt(sppParam);
        Map<String, String> map = new HashMap<>();
        ProductResultDto productResultDto = new ProductResultDto();
        ProductListDto productListDto = new ProductListDto();
        List<ProductDto> productList = Collections.emptyList();
        productListDto.setTotalItemCount(0);
        productListDto.setTotalPageCount(1);
        productResultDto.setMsg("검색된 제품이 없습니다.");
        productResultDto.setStatus("210");
        map.put("brand", params.get("brand"));
        map.put("word", params.get("word"));
        try{
            int count = mapper.getProductCount(map);
            logger.debug("ProductMapper.getProductCount totalItemCount : {}", count);
            productListDto.setTotalItemCount(count);
            if(count != 0){
                int totalPageCount = count % spp == 0 ? count/spp : count/spp + 1;
                productListDto.setTotalPageCount(totalPageCount);
                map.put("start", String.valueOf((pgno-1)*spp));
                map.put("offset", String.valueOf(spp));
                productList = mapper.getProductList(map);
                productResultDto.setData(productListDto);
                productResultDto.setStatus("200");
                StringBuilder sb = new StringBuilder();
                sb.append("검색된 제품은 총 ").append(count).append("건 입니다.");
                productResultDto.setMsg(sb.toString());

            }
        }catch(Exception e){
            productListDto.setTotalItemCount(0);
            productListDto.setTotalPageCount(1);
            logger.debug("ProductService.getProductList Exception 발생 : {}", e.toString());
            return productResultDto;

        }
        productListDto.setProductList(productList);
        return productResultDto;
    }

    @Override
    public ProductResultDto insertProduct(ProductDto productDto) throws Exception {
        ProductResultDto productResultDto = new ProductResultDto();
        productResultDto.setMsg("제품을 성공적으로 등록하였습니다.");
        productResultDto.setStatus("201");
        try{
            Integer productId = mapper.getProductIdbyproductName(productDto.getProductName());
            if(productId != null) throw new DuplicatedProductException(productDto.getProductName());
            mapper.insertProduct(productDto);
        }catch(SQLException e){
            productResultDto.setMsg("제품 등록에 실패하였습니다.");
            productResultDto.setStatus("500");
        }catch(DuplicatedProductException e){
            productResultDto.setMsg(e.getMessage());
            productResultDto.setStatus(e.getStatus());
        } finally{
            return productResultDto;
        }
    }


    @Override
    public ProductResultDto getProductDetailList(Map<String, String> params) throws Exception {
        ProductResultDto productResultDto = new ProductResultDto();
        productResultDto.setStatus("210");
        int count = 0;
        ProductDetailListDto productDetailListDto = new ProductDetailListDto();
        List<ProductDetailDto> productDetailList = Collections.emptyList();

        productDetailListDto.setTotalItemCount(0);
        productDetailListDto.setTotalPageCount(1);
        try{
            count = mapper.getProductDetailCountByproductId(params.get("productId"));
            if(count != 0){
                productDetailListDto.setTotalItemCount(count);
                int spp = Integer.parseInt(params.get("spp"));
                int totalPageCount = count % spp == 0 ? count/spp : count/spp + 1;
                productDetailListDto.setTotalPageCount(totalPageCount);
                int start = Integer.parseInt(params.get("pgno"));
                params.put("start", String.valueOf((start-1) * spp));
                params.put("offset", params.get("spp"));
                productDetailList = mapper.getProductDetailListByproductId(params);
                productResultDto.setStatus("200");
            }
            StringBuilder sb = new StringBuilder();
            sb.append("검색된 제품은 총 ").append(count).append("건 입니다.");
            productResultDto.setMsg(sb.toString());
        }catch(Exception e){
            productDetailListDto.setTotalItemCount(0);
            productDetailListDto.setTotalPageCount(1);
            productResultDto.setStatus("500");
            productResultDto.setMsg("처리 도중 에러가 발생하였습니다.");
            return productResultDto;
        }
        productDetailListDto.setProductDetailDtoList(productDetailList);
        productResultDto.setData(productDetailListDto);
        return productResultDto;
    }

    @Override
    public ProductResultDto insertProductItem(ProductWHDto productWHDto) throws Exception {
        ProductResultDto productResultDto = new ProductResultDto();
        productResultDto.setStatus("201");
        productResultDto.setMsg("재고를 성공적으로 추가하였습니다!");
        try{
            mapper.insertProductItem(productWHDto);
        }catch(DuplicateKeyException e){
            productResultDto.setStatus("501");
            productResultDto.setMsg("중복된 코드가 존재합니다.");
        } catch(DataIntegrityViolationException e){
            productResultDto.setMsg("잘못된 데이터입니다.");
            productResultDto.setStatus("502");
        }finally{
            return productResultDto;
        }
    }

    @Override
    public ProductResultDto searchProductByCode(String productCode) throws Exception {
        ProductResultDto productResultDto = new ProductResultDto();
        productResultDto.setMsg("해당하는 제품이 없습니다.");
        productResultDto.setStatus("210");
        ProductInfoDto productInfoDto = null;
        productResultDto.setData(new Object());
        try{
            logger.debug("ProductResultDto searchProductByCode params : {}", productCode);
            productInfoDto = mapper.searchProductByCode(productCode);

            logger.debug("productInfoDto is null : {}", (productInfoDto == null));

            productResultDto.setData(productInfoDto);
            productResultDto.setStatus("200");
            productResultDto.setMsg("조회된 결과가 존재합니다.");
        }catch(Exception e){
            productResultDto.setMsg("처리 도중 에러가 발생하였습니다.");
            productResultDto.setStatus("500");
            productResultDto.setData(new Object());
            return productResultDto;
        }
        return productResultDto;
    }
}
