package com.java.productservicecatalogue.repos;

import com.java.productservicecatalogue.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long>
{

}
