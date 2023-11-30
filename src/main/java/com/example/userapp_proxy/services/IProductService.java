package com.example.userapp_proxy.services;

import com.example.userapp_proxy.dtos.ProductDto;

public interface IProductService {
    String getAllProducts();

    String getSingleProduct(Long productId);

    String addNewProduct(ProductDto productDto);

    String updateProduct(ProductDto productDto);

    String deleteProduct(ProductDto productDto);
}
