package com.java.productservicecatalogue.services;

import com.java.productservicecatalogue.dtos.SortParam;
import com.java.productservicecatalogue.dtos.SortType;
import com.java.productservicecatalogue.models.Product;
import com.java.productservicecatalogue.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService
{
    @Autowired
    private ProductRepo productRepo;

    @Override
    public Page<Product> searchProduct(String query, Integer pageNumber, Integer pageSize, List<SortParam> sortParams)
    {
        Sort sort = null;
        if(sortParams.size()!=0)
        {
            if(sortParams.get(0).getSortType().equals(SortType.ASC))
                sort=Sort.by(sortParams.get(0).getAttribute());
            else
                sort=Sort.by(sortParams.get(0).getAttribute()).descending();

            for(int i=1;i<sortParams.size();i++)
            {
                if(sortParams.get(i).getSortType().equals(SortType.ASC))
                    sort=sort.and(Sort.by(sortParams.get(0).getAttribute()));
                else
                    sort=sort.and(Sort.by(sortParams.get(0).getAttribute()).descending());
            }
        }

        return productRepo.findProductByTitleEquals(query, PageRequest.of(pageNumber,pageSize,sort));
    }
}
