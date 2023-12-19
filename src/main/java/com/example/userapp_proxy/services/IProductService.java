package com.example.userapp_proxy.services;

import com.example.userapp_proxy.dtos.ProductDto;
import com.example.userapp_proxy.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Product getSingleProduct(Long productId);

    Product addNewProduct(ProductDto productDto);

    Product updateProduct(Long productId , Product product);

    String deleteProduct(ProductDto productDto);
}
