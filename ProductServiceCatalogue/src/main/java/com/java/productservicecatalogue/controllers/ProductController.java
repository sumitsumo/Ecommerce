package com.java.productservicecatalogue.controllers;


import com.java.productservicecatalogue.dtos.CategoryDto;
import com.java.productservicecatalogue.dtos.ProductDto;
import com.java.productservicecatalogue.models.Category;
import com.java.productservicecatalogue.models.Product;
import com.java.productservicecatalogue.services.ProductService;
import com.java.productservicecatalogue.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController
{

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductDto> getProducts()
    {
        List<ProductDto> response=new ArrayList<>();
        List<Product> productList= productService.getAllProducts();
        for(Product product:productList)
        {
            ProductDto productDto=toDto(product);
            response.add(productDto);
        }
        return response;
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductByID(@PathVariable("id") Long productId)
    {
        try {
            Product product = productService.getProductById(productId);
            if (product == null)
                throw new RuntimeException("Product not found");
            ProductDto productDto = toDto(product);
            return ResponseEntity
                    .ok(productDto);
        }catch (RuntimeException e) {
            throw e;
        }
    }
    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto)
    {
        Product product = toProduct(productDto);
        ProductDto response=toDto(productService.createProduct(product));
        return response;
    }
    @PutMapping("/products/{id}")
    public ProductDto replaceProduct(@PathVariable("id") Long id,  @RequestBody ProductDto productDto)
    {
        Product product = productService.replaceProduct(id, toProduct(productDto));
        return toDto(product);
    }
    public Product toProduct(ProductDto productDto)
    {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setAmount(productDto.getAmount());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setId(productDto.getCategory().getId());
            category.setName(productDto.getCategory().getName());
            product.setCategory(category);
        }
        return product;
    }
    public ProductDto toDto(Product product)
    {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setAmount(product.getAmount());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }

        return productDto;
    }


}
