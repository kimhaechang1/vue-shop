package com.khc.shop.product.controller;

import com.khc.shop.product.model.ProductDetailDto;
import com.khc.shop.product.model.ProductFromClientDto;
import com.khc.shop.product.model.ProductResultDto;
import com.khc.shop.product.model.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService service;


    public ProductController(ProductService service){
        this.service = service;
    }

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping
    protected ResponseEntity<ProductResultDto> getProductList(@RequestParam Map<String, String> params) {
        ProductResultDto productResultDto = null;
        logger.debug("ProductController.getProductList params : {}", params.toString());
        try{
            productResultDto = service.getProductList(params);
        }catch(Exception e){
            logger.debug("ProductController.getProductList Exception 발생 : {}", e.toString());
        }

        return new ResponseEntity<>(productResultDto, HttpStatus.OK);
    }

    @PostMapping
    protected ResponseEntity<ProductResultDto> insertProduct(ProductFromClientDto productFromClientDto){
        ProductResultDto productResultDto = null;
        logger.debug("ProductController.insertProduct requestBody : {}", productFromClientDto.toString());
        try{
            productResultDto = service.productInsert(productFromClientDto);
        }catch(Exception e){
            logger.debug("ProductController.insertProduct Exception 발생 : {}", e.toString());
        }
        return new ResponseEntity<ProductResultDto>(productResultDto, HttpStatus.OK);
    }

    @GetMapping("/${productId}")
    protected ResponseEntity<ProductResultDto> getProductDetailList(@RequestParam Map<String, String> params){
        ProductResultDto productResultDto = null;
        try{
            productResultDto = service.getProductDetailList(params);
        }catch(Exception e){
            logger.debug("ProductController.getProductDetailList Exception 발생 : {}", e.toString());
        }
        return new ResponseEntity<ProductResultDto>(productResultDto, HttpStatus.OK);
    }
}
