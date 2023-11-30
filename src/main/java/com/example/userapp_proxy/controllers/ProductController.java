package com.example.userapp_proxy.controllers;

import com.example.userapp_proxy.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")

public class ProductController {
    @GetMapping("")
    public String getSingleProduct(){
     return  "Get Controller";
    }

    @GetMapping("/{productId}")
    public String getSingleProductById(@PathVariable("productId") Long productId){
        return  "Get Controller " + productId;
    }

    @PostMapping()
    public String addNewProduct(@RequestBody ProductDto productDto){
       return "Adding new Product " + productDto;
    }


}
