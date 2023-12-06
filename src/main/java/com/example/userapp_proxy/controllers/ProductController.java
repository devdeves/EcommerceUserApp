package com.example.userapp_proxy.controllers;

import com.example.userapp_proxy.dtos.ProductDto;
import com.example.userapp_proxy.services.IProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")

public class ProductController {
    IProductService productService;

    public ProductController(IProductService productService){
        this.productService = productService;
    }


    @GetMapping("/{productId}")
    public String getSingleProduct(@PathVariable("productId") Long productId){
       productService.getSingleProduct(productId);
        return  "Get Controller " + productId;

    }

    @PostMapping()
    public String addNewProduct(@RequestBody ProductDto productDto){
       return "Adding new Product " + productDto;
    }


}
