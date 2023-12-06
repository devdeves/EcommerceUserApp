package com.example.userapp_proxy.services;

import com.example.userapp_proxy.dtos.ProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

public class ProductService implements IProductService {

    private RestTemplateBuilder restTemplateBuilder;

    public ProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public String getAllProducts(){
        return null;
    }

    @Override
    public String getSingleProduct(Long productId){
        RestTemplate restTemplate = restTemplateBuilder.build();
    }

    @Override
    public String addNewProduct(ProductDto productDto){
        return null;
    }
    @Override
    public String updateProduct(ProductDto productDto){
        return null;
    }
    @Override
    public String deleteProduct(ProductDto productDto){
        return null;
    }
}

