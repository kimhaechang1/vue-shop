package com.khc.shop.product.model.service;

import com.khc.shop.product.model.ProductDto;
import com.khc.shop.product.model.ProductListDto;
import com.khc.shop.product.model.ProductResultDto;
import com.khc.shop.product.model.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductMapper mapper;

    public ProductServiceImpl(ProductMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ProductResultDto getProductList(Map<String, String> params) throws Exception {
        int pgno = Integer.parseInt(params.get("pgno"));
        int spp = Integer.parseInt(params.get("spp"));
        Map<String, String> map = new HashMap<>();
        ProductResultDto productResultDto = new ProductResultDto();
        ProductListDto productListDto = new ProductListDto();
        map.put("cate", params.get("cate"));
        map.put("word", params.get("word"));
        int count = mapper.getProductCount(map);
        int totalItemCount = count;

        productListDto.setTotalItemCount(count);
        int totalPageNumber;
        if(totalItemCount == 0){
            totalPageNumber = 1;
        }else{
            totalPageNumber = count % spp == 0 ? count/spp : count/spp + 1;
        }
        productListDto.setTotalPageCount(totalPageNumber);
        map.put("start", String.valueOf((pgno-1)*spp));
        map.put("offset", String.valueOf(spp));

        List<ProductDto> productList = mapper.getProductList(map);
        productListDto.setProductList(productList);
        productResultDto.setData(productListDto);
        productResultDto.setStatus("200");
        productResultDto.setMsg("리스트 불러오기 성공!");
        return productResultDto;
    }
}
