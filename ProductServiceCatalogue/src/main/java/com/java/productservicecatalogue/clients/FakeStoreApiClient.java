package com.java.productservicecatalogue.clients;

import com.java.productservicecatalogue.dtos.FakeStoreProductDto;
import com.java.productservicecatalogue.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreApiClient
{
    @Autowired
    RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();

    //product by id
    public FakeStoreProductDto getProductById(Long id)
    {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                requestForEntity("https://fakestoreapi.com/products/{id}",HttpMethod.GET,null, FakeStoreProductDto.class,id);

        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();
        if(fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200)) &&
                fakeStoreProductDto!=null) {
            return fakeStoreProductDto;
        }

        return null;
    }

    //get all products
    public FakeStoreProductDto[] getAllProducts()
    {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto[] fakeStoreProductDtos
                =restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class).getBody();

        if(fakeStoreProductDtos!=null) {
            return fakeStoreProductDtos;
        }
        return null;
    }

    //create a product
    public FakeStoreProductDto createProduct(FakeStoreProductDto fakeStoreProductDto)
    {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity
                =requestForEntity("https://fakestoreapi.com/products",HttpMethod.POST,fakeStoreProductDto,FakeStoreProductDto.class);
        if(fakeStoreProductDtoResponseEntity.getBody()!=null)
            return fakeStoreProductDtoResponseEntity.getBody();
        return null;
    }

    //replace a product
    public FakeStoreProductDto replaceProduct(Long id,FakeStoreProductDto fakeStoreProductDto)
    {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity
                =requestForEntity("https://fakestoreapi.com/products/{id}",HttpMethod.PUT,fakeStoreProductDto, FakeStoreProductDto.class,id);
        FakeStoreProductDto responseDto=fakeStoreProductDtoResponseEntity.getBody();
        if(responseDto!=null) {
            return responseDto;
        }
        return null;
    }

    private <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
}
