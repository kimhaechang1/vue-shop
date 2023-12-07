package com.khc.shop.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khc.shop.product.controller.ProductController;
import com.khc.shop.product.model.ProductDto;
import com.khc.shop.product.model.ProductWHDto;
import com.khc.shop.product.model.mapper.ProductMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc // MockMvc 객체를 주입받을 수 있음
public class ProductAPITest {

    @Autowired
    private ProductController productController;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Product Insert Test")
    void productInsertTest() throws Exception{
        ProductDto productDto = new ProductDto();
        productDto.setProductBrand("NIKE");
        productDto.setProductDescription("test description");
        productDto.setProductName("test shoes");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(
                post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(productDto))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("201"));

        mockMvc.perform(
                        get("/product?brand="+productDto.getProductBrand()+"&word="+productDto.getProductName())

                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("200"))
                .andExpect(jsonPath("$.data.productList[0].productName").value(productDto.getProductName()));
    }

    @Test
    @DisplayName("Product Insert But Duplicated ProductName Test")
    public void productInsertDuplicatedProductName() throws Exception{
        ProductDto productDto = new ProductDto();
        productDto.setProductBrand("NIKE");
        productDto.setProductDescription("test description");
        productDto.setProductName("test shoes");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(
                        post("/product")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(productDto))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("220"));
    }

    @Test
    @DisplayName("ProductItem Insert Test")
    public void productItemInsertTest() throws Exception{
        ProductWHDto productWHDto = new ProductWHDto();
        productWHDto.setProductSize(270);
        productWHDto.setProductCode("TEST01");
        productWHDto.setProductId(36);
        ObjectMapper mapper =new ObjectMapper();
        mockMvc.perform(
                post("/product/"+productWHDto.getProductId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(productWHDto))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("201"));
        mockMvc.perform(
                get("/product/search/"+productWHDto.getProductCode())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("200"))
                .andExpect(jsonPath("$.data.productCode").value(productWHDto.getProductCode()));
    }

    @Test
    @DisplayName("ProductItem Insert But productId Not Found Test")
    public void productItemInsertProductIdNotFound() throws Exception{
        ProductWHDto productWHDto = new ProductWHDto();
        productWHDto.setProductSize(270);
        productWHDto.setProductCode("TEST01");
        productWHDto.setProductId(99);
        ObjectMapper mapper =new ObjectMapper();
        mockMvc.perform(
                        post("/product/"+productWHDto.getProductId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(productWHDto))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("502"));

    }

    @Test
    @DisplayName("ProductItem Insert With Duplicated ProductCode Test")
    public void productItemInsertDuplicatedProductCode() throws Exception{
        ProductWHDto productWHDto = new ProductWHDto();
        productWHDto.setProductSize(270);
        productWHDto.setProductCode("DF212412F");
        productWHDto.setProductId(1);
        ObjectMapper mapper =new ObjectMapper();
        mockMvc.perform(
                        post("/product/"+productWHDto.getProductId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(productWHDto))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("501"));
    }

    @Test
    @DisplayName("productDetailList by productId Test")
    public void productDetailListByProductIdTest() throws Exception{

        String productId = "1";
        int expectedTotalItemCount = productMapper.getProductDetailCountByproductId(productId);
        if(expectedTotalItemCount == 0){
            fail("msg : \n ==> productDetailListByProductIdTest productId : "+productId+" but item count 0 !!!");
            return;
        }
        int spp = 9;
        int expectedTotalPageCount;
        if(spp > expectedTotalItemCount){
            expectedTotalPageCount = 1;
        }else{
            expectedTotalPageCount = expectedTotalItemCount % spp == 0 ? expectedTotalItemCount / spp : expectedTotalItemCount / spp + 1;
        }
        mockMvc.perform(
                get("/product/"+productId+"?pgno=1&spp="+spp)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("200"))
                .andExpect(jsonPath("$.data.totalPageCount").value(expectedTotalPageCount))
                .andExpect(jsonPath("$.data.totalItemCount").value(expectedTotalItemCount));
    }

    @Test
    @DisplayName("productDetailList by productId if not exist")
    public void productDetailListByProductIdTestIfNotExist() throws Exception{
        String productId = "1";
        int count = productMapper.getProductDetailCountByproductId(productId);
        if(count > 0){
            fail("msg : \n ==> productId : "+productId+" has productDetailItems!!");
            return;
        }
        mockMvc.perform(
                get("/product/"+productId+"?pgno=1&spp=9")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("210"))
                .andExpect(jsonPath("$.data.totalItemCount").value(0))
                .andExpect(jsonPath("$.data.totalPageCount").value(1))
                .andExpect(jsonPath("$.data.productDetailDtoList.size()").value(0));
    }


}
