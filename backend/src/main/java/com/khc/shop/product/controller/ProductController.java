package com.khc.shop.product.controller;

import com.khc.shop.product.model.ProductDto;
import com.khc.shop.product.model.ProductResultDto;
import com.khc.shop.product.model.ProductWHDto;
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

    // 제품목록 들고오기
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

    // 제품 추가하기
    @PostMapping
    protected ResponseEntity<ProductResultDto> insertProduct(@RequestBody ProductDto productDto){
        ProductResultDto productResultDto = null;
        logger.debug("ProductController.insertProduct requestBody : {}", productDto.toString());
        try{
            productResultDto = service.insertProduct(productDto);
        }catch(Exception e){
            logger.debug("ProductController.insertProduct Exception 발생 : {}", e.toString());
        }
        logger.debug("ProductController.insertProduct response : {}", productResultDto);
        return new ResponseEntity<ProductResultDto>(productResultDto, HttpStatus.OK);
    }

    // 제품ID에 따른 재고 목록 들고오기
    @GetMapping("/warehouse/{productId}")
    protected ResponseEntity<ProductResultDto> getProductDetailList(@RequestParam Map<String, String> params, @PathVariable("productId") String productId){

        ProductResultDto productResultDto = null;
        logger.debug("params : {} productId : {}", params.toString(), productId);
        try{
            params.put("productId", productId);
            productResultDto = service.getProductDetailList(params);
        }catch(Exception e){
            logger.debug("ProductController.getProductDetailList Exception 발생 : {}", e.toString());
        }
        logger.debug("ProductController.getProductDetailList result : {}", productResultDto);
        return new ResponseEntity<ProductResultDto>(productResultDto, HttpStatus.OK);
    }

    // 제품ID에 대한 재고 추가하기
    @PostMapping("/{productId}")
    protected ResponseEntity<ProductResultDto> insertProductItem(@RequestBody ProductWHDto productWHDto){
        ProductResultDto productResultDto = null;
        try{
            productResultDto = service.insertProductItem(productWHDto);
        }catch(Exception e){
            logger.debug("ProductController.insertProductItem Exception 발생 : {}", e.toString());
        }
        logger.debug("ProductController.insertProductItem response : {}", productResultDto.toString());
        return new ResponseEntity<ProductResultDto>(productResultDto, HttpStatus.OK);
    }



    // 제품코드로 제품 및 재고 아이템 들고오기
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

    // 제품 수정하기
    @PutMapping("/{productId}")
    protected ResponseEntity<ProductResultDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable String productId){
        ProductResultDto productResultDto = null;
        try{
            productDto.setProductId(Integer.parseInt(productId));
            productResultDto = service.updateProduct(productDto);
        }catch(Exception e){
            logger.debug("ProductController.updateProduct Exception 발생 : {}", e.toString());
        }
        return new ResponseEntity<ProductResultDto>(productResultDto, HttpStatus.OK);
    }

    // 제품에 따른 재고 수정하기
    @PutMapping("/warehouse/{productCode}")
    protected ResponseEntity<ProductResultDto> updateProductItem(@RequestBody ProductWHDto productWHDto, @PathVariable String productCode){
        ProductResultDto productResultDto = null;
        try{
            productWHDto.setProductCode(productCode);
            productResultDto = service.updateProductItem(productWHDto);
        }catch(Exception e){
            logger.debug("ProductController.updateProductItem Exception 발생 : {}", e.toString());
        }
        return new ResponseEntity<ProductResultDto>(productResultDto, HttpStatus.OK);
    }

    // 제품 삭제하기
    @DeleteMapping("{productId}")
    protected ResponseEntity<ProductResultDto> deleteProduct(@PathVariable String productId){
        ProductResultDto productResultDto = null;
        try{
            productResultDto = service.deleteProduct(Integer.parseInt(productId));
        }catch(Exception e){
            logger.debug("ProductController.deleteProduct Exception 발생 : {}", e.toString());
        }
        return new ResponseEntity<ProductResultDto>(productResultDto, HttpStatus.OK);
    }

    // 제품에 따른 재고 삭제하기
    @DeleteMapping("/warehouse/{productCode}")
    protected ResponseEntity<ProductResultDto> deleteProductItem(@PathVariable String productCode){
        ProductResultDto productResultDto = null;
        try{
            productResultDto = service.deleteProductItem(productCode);
        }catch(Exception e){
            logger.debug("ProductController.deleteProductItem Exception 발생 : {}", e.toString());
        }
        return new ResponseEntity<ProductResultDto>(productResultDto, HttpStatus.OK);
    }
}
