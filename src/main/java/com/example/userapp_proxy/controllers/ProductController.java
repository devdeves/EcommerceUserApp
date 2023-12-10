package com.example.userapp_proxy.controllers;

import com.example.userapp_proxy.dtos.ProductDto;
import com.example.userapp_proxy.models.Product;
import com.example.userapp_proxy.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")

public class ProductController {
    IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId) {
        try {

          //  System.out.println(productId);
            Product product = this.productService.getSingleProduct(productId);
//            if(productId < 1) {
//                throw new IllegalAccessException("Some Went Wrong!");
//            }
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Accept", "application/json");
            headers.add("Content-Type", "application/json");
            headers.add("auth-token", "token-to-access");
            ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, headers,HttpStatus.OK);
            return responseEntity;
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

}

    @PostMapping()
    public String addNewProduct(@RequestBody ProductDto productDto){
       return "Adding new Product " + productDto;
    }


}
