package com.java.productservicecatalogue.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchProductDTO
{
    private String query;
    private Integer pageSize;
    private Integer pageNumber;
    private List<SortParam> sortParamList = new ArrayList<>();
}
