package com.java.productservicecatalogue.repos;

import com.java.productservicecatalogue.models.Category;
import com.java.productservicecatalogue.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest

class CategoryRepoTest
{
    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Transactional
    public void testFetchTypes()
    {
        Category category = categoryRepo.findById(1L).get();
        for(Product product : category.getProducts())
            System.out.println(product.getDescription().toString());
    }
}