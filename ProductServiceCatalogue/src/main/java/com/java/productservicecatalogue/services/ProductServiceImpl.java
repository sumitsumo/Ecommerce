package com.java.productservicecatalogue.services;

import com.java.productservicecatalogue.clients.FakeStoreApiClient;
import com.java.productservicecatalogue.dtos.FakeStoreProductDto;
import com.java.productservicecatalogue.dtos.ProductDto;
import com.java.productservicecatalogue.models.Category;
import com.java.productservicecatalogue.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;



@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    private FakeStoreApiClient fakeStoreApiClient;
    //TO-Do: implement constructor injections

    @Override
    public List<Product> getAllProducts()
    {
        FakeStoreProductDto[] fakeStoreProductDtos = fakeStoreApiClient.getAllProducts();
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos)
        {
            Product product=toProduct(fakeStoreProductDto);
            products.add(product);
        }
        return products;
    }
    @Override
    public Product getProductById(Long id)
    {
        FakeStoreProductDto fakeStoreProductDto=fakeStoreApiClient.getProductById(id);
        if(fakeStoreProductDto!=null)
            return toProduct(fakeStoreProductDto);
        return null;
    }
    @Override
    public Product createProduct(Product product)
    {
        FakeStoreProductDto fakeStoreProductDto=toDto(product);
        FakeStoreProductDto responseDto=fakeStoreApiClient.createProduct(fakeStoreProductDto);
        return toProduct(responseDto);
    }

    @Override
    public Product replaceProduct(Long id, Product product)
    {
        FakeStoreProductDto fakeStoreProductDto = toDto(product);
        FakeStoreProductDto fakeStoreProductDtoResponse =
                fakeStoreApiClient.replaceProduct(id, fakeStoreProductDto);
        return toProduct(fakeStoreProductDtoResponse);
    }

    //TO-DO: implement in mapper classes
    public FakeStoreProductDto toDto(Product product)
    {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getAmount());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        if(product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }

        return fakeStoreProductDto;
    }
    public Product toProduct(FakeStoreProductDto fakeStoreProductDto)
    {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setAmount(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
