package com.java.productservicecatalogue.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto
{
    private String id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
}
