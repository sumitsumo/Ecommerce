package com.java.productservicecatalogue.services;

import com.java.productservicecatalogue.models.Product;
import com.java.productservicecatalogue.models.State;
import com.java.productservicecatalogue.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StorageProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Product> getAllProducts()
    {
        if(productRepo.findAll().isEmpty())
            throw new RuntimeException("Product list is empty");
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Long id)
    {
        Optional<Product> optionalProduct= productRepo.findById(id);
        if(optionalProduct.isPresent())
            return optionalProduct.get();
        throw new RuntimeException("Product not found");
    }

    @Override
    public Product createProduct(Product product)
    {
        Product response= productRepo.save(product);
//        response.setState(State.VALID);
        return response;
    }

    @Override
    public Product replaceProduct(Long id, Product updatedProduct) {
        // Fetch the existing product from the database
        Product existingProduct = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Update only the fields that should change
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setTitle(updatedProduct.getTitle());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setAmount(updatedProduct.getAmount());
        existingProduct.setImageUrl(updatedProduct.getImageUrl());
        existingProduct.setLastUpdatedAt(LocalDateTime.now());
        return productRepo.save(existingProduct);
    }

}
