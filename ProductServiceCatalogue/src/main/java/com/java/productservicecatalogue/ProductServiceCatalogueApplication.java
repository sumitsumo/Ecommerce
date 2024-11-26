package com.java.productservicecatalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
public class ProductServiceCatalogueApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceCatalogueApplication.class, args);
    }

}
