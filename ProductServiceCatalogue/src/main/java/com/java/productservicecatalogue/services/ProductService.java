package com.java.productservicecatalogue.services;

import com.java.productservicecatalogue.dtos.ProductDto;
import com.java.productservicecatalogue.models.Product;

import java.util.List;

public interface ProductService
{
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product replaceProduct(Long id, Product product);
}
