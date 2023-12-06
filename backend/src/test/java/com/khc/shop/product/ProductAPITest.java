package com.khc.shop.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khc.shop.product.controller.ProductController;
import com.khc.shop.product.model.ProductFromClientDto;
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
        ProductFromClientDto productFromClientDto = new ProductFromClientDto();
        productFromClientDto.setProductCode("DEFEXDF19");
        productFromClientDto.setProductBrand("NIKE");
        productFromClientDto.setProductSize(270);
        productFromClientDto.setProductDescription("test description");
        productFromClientDto.setProductName("나이키 덩크 로우 SP");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(
                post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(productFromClientDto))
        ).andExpect(status().isOk());
        mockMvc.perform(
                        get("/product/search/"+productFromClientDto.getProductCode())

                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.productId").value(1));
    }

    @Test
    @DisplayName("insert Product Transaction Test if invoke Exception")
    public void productInsertTransactionTest() throws Exception{
        ProductFromClientDto productFromClientDto = new ProductFromClientDto();
        productFromClientDto.setProductCode("DEFEXDF19");
        productFromClientDto.setProductName("나이키 덩크 로우 SP");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(
                post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(productFromClientDto))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("500"));
        mockMvc.perform(
                get("/product/search/"+productFromClientDto.getProductCode())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("210"));
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
