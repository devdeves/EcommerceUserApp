package com.example.userapp_proxy.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Categories extends BaseModel{
    private String name;
    private String descriptions;
//   private String images;
@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product>productList;



}
