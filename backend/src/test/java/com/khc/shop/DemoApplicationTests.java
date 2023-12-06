package com.khc.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khc.shop.product.controller.ProductController;
import com.khc.shop.product.model.ProductFromClientDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc // MockMvc 객체를 주입받을 수 있음

class DemoApplicationTests {

	@Autowired
	private ProductController productController;

	@Autowired
	MockMvc mvc;
	@Test
	@DisplayName("Product Insert Test")
	void productInsertTest() throws Exception{
		ProductFromClientDto productFromClientDto = new ProductFromClientDto();
		productFromClientDto.setProductCode("DEFEXDF12");
		productFromClientDto.setProductBrand("NIKE");
		productFromClientDto.setProductSize(270);
		productFromClientDto.setProductDescription("test description");
		productFromClientDto.set
		ObjectMapper mapper = new ObjectMapper();

	}

}
