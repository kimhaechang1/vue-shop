package com.khc.shop.product.controller;

import com.khc.shop.product.model.ProductResultDto;
import com.khc.shop.product.model.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    protected ResponseEntity<?> getProductList(@RequestParam Map<String, String> params) {
        logger.debug("degub logger test");
        ProductResultDto productResultDto = null;
        try{
            productResultDto = service.getProductList(params);
        }catch(Exception e){
            System.out.println(e);
        }

        return new ResponseEntity<>(productResultDto, HttpStatus.OK);
    }
}
