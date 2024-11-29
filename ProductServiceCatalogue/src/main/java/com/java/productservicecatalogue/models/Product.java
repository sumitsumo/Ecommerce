package com.java.productservicecatalogue.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Product extends BaseModel
{
    private String title;
    private String description;
    private String imageUrl;
    private Double amount;
//    @JsonBackReference
    @JsonManagedReference // Managed reference for serialization
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
}
