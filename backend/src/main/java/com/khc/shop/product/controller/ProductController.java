package com.khc.shop.product.controller;

import com.khc.shop.product.model.ProductFromClientDto;
import com.khc.shop.product.model.ProductResultDto;
import com.khc.shop.product.model.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        logger.debug("ProductController.getProductList result : {}", productResultDto);
        return new ResponseEntity<>(productResultDto, HttpStatus.OK);
    }

    @PostMapping
    protected ResponseEntity<ProductResultDto> insertProduct(@RequestBody ProductFromClientDto productFromClientDto){
        ProductResultDto productResultDto = null;
        logger.debug("ProductController.insertProduct requestBody : {}", productFromClientDto.toString());
        try{
            productResultDto = service.productInsert(productFromClientDto);
        }catch(Exception e){
            logger.debug("ProductController.insertProduct Exception 발생 : {}", e.toString());
        }
        logger.debug("ProductController.insertProduct result : {}", productResultDto);
        return new ResponseEntity<ProductResultDto>(productResultDto, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    protected ResponseEntity<ProductResultDto> getProductDetailList(@RequestParam Map<String, String> params, @PathVariable("productId") String productId){
        ProductResultDto productResultDto = null;
        try{
            params.put("productId", productId);
            productResultDto = service.getProductDetailList(params);
        }catch(Exception e){
            logger.debug("ProductController.getProductDetailList Exception 발생 : {}", e.toString());
        }
        logger.debug("ProductController.getProductDetailList result : {}", productResultDto);
        return new ResponseEntity<ProductResultDto>(productResultDto, HttpStatus.OK);
    }

    @GetMapping("/search/{productCode}")
    protected ResponseEntity<ProductResultDto> searchProductByCode(@PathVariable String productCode){
        ProductResultDto productResultDto  = null;
        try{
            productResultDto = service.searchProductByCode(productCode);
        }catch(Exception e){
            logger.debug("ProductController.searchProductByCode Exception 발생 : {}", e.toString());

        }
        logger.debug("ProductController.searchProductByCode result : {}", productResultDto);

        return new ResponseEntity<ProductResultDto>(productResultDto, HttpStatus.OK);
    }
}
