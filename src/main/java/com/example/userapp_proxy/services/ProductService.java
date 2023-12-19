package com.example.userapp_proxy.services;

import jakarta.annotation.Nullable;
import com.example.userapp_proxy.dtos.ProductDto;
import com.example.userapp_proxy.models.Categories;
import com.example.userapp_proxy.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;


import java.util.ArrayList;
import java.util.List;

@Service

public class ProductService implements IProductService {

    private RestTemplateBuilder restTemplateBuilder;

    public ProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        // Here Item is list of product so we cannot directly use LIST  is a generic collection thatswhy it on run time forgot what ds it is ...so we use array here //
            ResponseEntity<ProductDto[]> productDtos = restTemplate.getForEntity("https://fakestoreapi.com/products",ProductDto[].class);
        List<Product> listofProduct = new ArrayList<>();

        // Now iterate item by item and get values from list of Product //
         for(ProductDto productDto :productDtos.getBody() ){
             Product product = new Product();
             product.setId(productDto.getId());
             product.setTitle(productDto.getTitle());
             product.setPrice(productDto.getPrice());
             //Category
             Categories categories = new Categories();
             categories.setName(productDto.getCategory()); // set categories name in categoryDTo
             product.setCategory(categories); // get category Name and  set into ProductDTO
             product.setImageUrl(productDto.getImage());
             listofProduct.add(product);
         }
        return listofProduct;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{productId}", ProductDto.class,productId);
        Product product = getProduct(productDto.getBody());
        return product;

    }

    @Override
    public Product addNewProduct(ProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
         restTemplate.postForEntity("https://fakestoreapi.com/products",productDto,ProductDto.class);
         Product product = getProduct(productDto);
         return  product;
    }

   
    @Override
    public Product updateProduct(Long productId ,Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setImage(product.getImageUrl());
        productDto.setPrice(product.getPrice());
        productDto.setTitle(product.getTitle());
        productDto.setCategory(product.getCategory().getName());

        ResponseEntity<ProductDto> productDtoResponseEntity = requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                productDto,
                ProductDto.class,
                productId
        );

        ProductDto productDto1 = productDtoResponseEntity.getBody();
        return getProduct(productDto1);
//
    }

//    private ResponseEntity<ProductDto> requestForEntity(HttpMethod patch, String url, ProductDto productDto, Class<ProductDto> productDtoClass, Long productId) {
//    }


    @Override
    public String deleteProduct(ProductDto productDto) {
        return "";
    }

    private Product getProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Categories category = new Categories();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        product.setDescription(productDto.getDescription());
        return product;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();
        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }


}

