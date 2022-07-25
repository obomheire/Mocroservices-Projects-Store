package com.zackoverflow.product;

import com.zackoverflow.product.dto.Category;
import com.zackoverflow.product.dto.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {

/*  NB: Because you can query with multiple String e,g (String category, brand)
    so the 0 here will mean that you only want to query with the String at index 0
    which will be "category". "Category.name" means that you want to query with the name, if
    you want to query with the brand it will be "Category.brand"*/
    @Query("{'Category.name':?0}")
    List<Product> findByCategory(String category);
}
