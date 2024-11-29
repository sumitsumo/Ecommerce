package com.java.productservicecatalogue.services;

import com.java.productservicecatalogue.dtos.SortParam;
import com.java.productservicecatalogue.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchService
{
     Page<Product> searchProduct(String query, Integer pageNumber, Integer pageSize, List<SortParam> sortParams);
}
