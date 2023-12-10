package com.khc.shop.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khc.shop.product.controller.ProductController;
import com.khc.shop.product.model.ProductDto;
import com.khc.shop.product.model.ProductInfoDto;
import com.khc.shop.product.model.ProductWHDto;
import com.khc.shop.product.model.mapper.ProductMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                .andExpect(jsonPath("$.status").value("501"));
    }

    @Test
    @DisplayName("ProductItem Insert Test")
    public void productItemInsertTest() throws Exception{
        ProductWHDto productWHDto = new ProductWHDto();
        productWHDto.setProductSize(270);
        productWHDto.setProductCode("TEST01");
        productWHDto.setProductId(33);
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
        productWHDto.setProductCode("TEST02");
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
        productWHDto.setProductCode("TEST01");
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
    @DisplayName("productList Test")
    public void productList() throws Exception{
        Map<String, String> map = new HashMap<>();
        map.put("pgno","1");
        int pgno = Integer.parseInt(map.get("pgno"));
        map.put("spp","9");
        int spp = Integer.parseInt(map.get("spp"));
        int expectedTotalItemCount = productMapper.getProductCount(map);
        String expectedStatus = expectedTotalItemCount == 0 ? "210" : "200";
        int expectedTotalPageCount;
        if(expectedTotalItemCount == 0 || (pgno-1) * spp > expectedTotalItemCount) expectedTotalPageCount = 1;
        else expectedTotalPageCount = expectedTotalItemCount % spp == 0 ? expectedTotalItemCount / spp : expectedTotalItemCount / spp + 1;
        mockMvc.perform(
                get("/product")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(expectedStatus))
                .andExpect(jsonPath("$.data.totalItemCount").value(expectedTotalItemCount))
                .andExpect(jsonPath("$.data.totalPageCount").value(expectedTotalPageCount));

    }


    @Test
    @DisplayName("productWHList by productId Test")
    public void productDetailListByProductId() throws Exception{
        String productId = "1";
        Map<String, String> map = new HashMap<>();
        map.put("productId", productId);
        int expectedTotalItemCount = productMapper.getProductWHCountByProductId(map);
        String expectedStatus = expectedTotalItemCount==0 ? "210" : "200";
        mockMvc.perform(
                get("/product/"+productId)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(expectedStatus))
                .andExpect(jsonPath("$.data.size()").value(expectedTotalItemCount));
    }

   @Test
   @DisplayName("productWHList by productId With size Param Test")
   public void productWHListWithSizeParam() throws Exception{
        String productId = "1";
        String size = "260";
        Map<String, String> map =new HashMap<>();
        map.put("productId", productId);
        map.put("size",size);
        int expectedTotalItemCount = productMapper.getProductWHCountByProductId(map);
        String expectedStatus = expectedTotalItemCount== 0 ? "210" : "200";
        mockMvc.perform(
                get("/product/"+productId+"?size="+size)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(expectedStatus))
                .andExpect(jsonPath("$.data.size()").value(expectedTotalItemCount));

   }

   @Test
   @DisplayName("product Update Test")
   public void productUpdateTest() throws Exception{
        ProductDto productDto = new ProductDto();
        productDto.setProductId(33);
        productDto.setProductDescription("update test description1");
        productDto.setProductName("update test1");
        productDto.setProductBrand("NIKE");
        ObjectMapper mapper = new ObjectMapper();
        int cnt = productMapper.getProductCountByProductId(productDto.getProductId());
        String expectedStatus = cnt == 0 ? "502" : "200";
        mockMvc.perform(
                put("/product/"+productDto.getProductId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(productDto))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(expectedStatus));
        Map<String, String> map = new HashMap<>();
        map.put("word",productDto.getProductName());
        cnt = productMapper.getProductCount(map);
        if(cnt != 1){
            fail("product update fail!!!");
        }
   }

   @Test
   @DisplayName("productItem update Test")
   public void productItemUpdateTest() throws Exception{
        String productCode = "TEST01";
        ProductWHDto productWHDto = new ProductWHDto();
        productWHDto.setProductCode(productCode);
        productWHDto.setProductSize(270);
        productWHDto.setProductId(33);
        ObjectMapper mapper =new ObjectMapper();
        int cnt = productMapper.getProductCountByProductId(productWHDto.getProductId());
        String expectedStatus = cnt == 0 ? "502" : "200";
        mockMvc.perform(
                put("/product/warehouse/"+productCode)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(productWHDto))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(expectedStatus));
        if(cnt == 0) return;
        Map<String, String> map = new HashMap<>();
        map.put("productId", String.valueOf(productWHDto.getProductId()));
        ProductInfoDto productInfoDto = productMapper.searchProductByCode(productCode);
        int id = productInfoDto.getProductId();
        int size = productInfoDto.getProductSize();
        if(size != productWHDto.getProductSize() || id != productWHDto.getProductId()){
            fail("productItem update fail !!!");
        }
   }


}
