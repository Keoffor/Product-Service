package com.webapp.kenTech.service;

import com.webapp.kenTech.dto.ProductRequest;
import com.webapp.kenTech.dto.ProductResponse;
import com.webapp.kenTech.model.Product;
import com.webapp.kenTech.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;


    public void createProduct(ProductRequest request){
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
        productRepository.save(product);
        log.info("product {} is saved.", product.getId());


    }

    public List<ProductResponse> getAllProduct() {
        List<Product> products = productRepository.findAll();
       return products.stream().map(product -> mapToProductResponse(product)).toList();

    }

    private ProductResponse mapToProductResponse(Product product) {
        return  ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();

    }
}
