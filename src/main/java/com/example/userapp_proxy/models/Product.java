package com.example.userapp_proxy.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;
    private Categories category;
    private String imageUrl;

}
