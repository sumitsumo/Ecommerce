package com.java.productservicecatalogue.dtos;

import com.java.productservicecatalogue.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class ProductDto
{
    private String id;
    private String title;
    private String description;
    private String imageUrl;
    private Double amount;
    private CategoryDto category;
}
