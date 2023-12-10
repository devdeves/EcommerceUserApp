package com.example.userapp_proxy.services;

import com.example.userapp_proxy.dtos.ProductDto;
import com.example.userapp_proxy.models.Product;

public interface IProductService {
    String getAllProducts();

    Product getSingleProduct(Long productId);

    String addNewProduct(ProductDto productDto);

    String updateProduct(ProductDto productDto);

    String deleteProduct(ProductDto productDto);
}
