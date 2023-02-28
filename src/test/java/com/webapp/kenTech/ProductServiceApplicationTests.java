package com.webapp.kenTech;

import com.webapp.kenTech.controller.ProductController;
import com.webapp.kenTech.dto.ProductRequest;
import com.webapp.kenTech.dto.ProductResponse;

import com.webapp.kenTech.repository.ProductRepository;
import com.webapp.kenTech.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@Testcontainers
@AutoConfigureMockMvc
public class ProductServiceApplicationTests {


//	@Container
//	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0.4");
	@InjectMocks
	private ObjectMapper objectMapper;

	@Mock
	ProductService productService;

	@Autowired
	ProductRepository productRepository;
	@Autowired
	private MockMvc mockMvc;


//	@DynamicPropertySource
//	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
//		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//	}


//	@Test
//	public void shouldCreateProduct() throws Exception {
//		ProductRequest productRequest = getProductRequest();
//		String requestString = objectMapper.writeValueAsString(productRequest);
//		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(requestString))
//				.andExpect(status().isCreated());
//		Assertions.assertEquals(1, productRepository.findAll().size());
//
//	}
	@Test
	@DisplayName("Should List All Posts When making GET request to endpoint - /api/product")
	public void shouldGetProduct() throws Exception {
		ProductResponse response1 = new ProductResponse("01", "Apple","iphone13",BigDecimal.valueOf(1400));
		ProductResponse response2 = new ProductResponse("02", "Samsung","A713",BigDecimal.valueOf(1200));
		Mockito.when(productService.getAllProduct()).thenReturn(List.of(response1,response2));
		mockMvc.perform(MockMvcRequestBuilders.get("/api/product"))
				.andExpect(status().is(200));


	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("iphone")
				.description("apple")
				.price(BigDecimal.valueOf(1200))
				.build();
	}

}
