package com.java.productservicecatalogue.controllers;


import com.java.productservicecatalogue.dtos.SearchProductDTO;
import com.java.productservicecatalogue.models.Product;
import com.java.productservicecatalogue.services.ProductService;
import com.java.productservicecatalogue.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController
{
    @Autowired
    private SearchService searchService;

    @PostMapping
    private Page<Product> searchProduct(@RequestBody SearchProductDTO searchProductDTO)
    {
        return searchService.searchProduct(
                searchProductDTO.getQuery(),searchProductDTO.getPageNumber(),searchProductDTO.getPageSize(),searchProductDTO.getSortParamList());

    }
}
