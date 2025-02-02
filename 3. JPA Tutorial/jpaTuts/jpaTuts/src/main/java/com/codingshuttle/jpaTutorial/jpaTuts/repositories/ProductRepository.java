package com.codingshuttle.jpaTutorial.jpaTuts.repositories;

import com.codingshuttle.jpaTutorial.jpaTuts.entities.ProductEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
//    List<ProductEntity> findByTitle(String title);

    // for more inbuilt query refer 3.3 week vdo or https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

    //SORTING AND PAGINATION

//    List<ProductEntity> findByTitleOrderByPrice(String title);

//    List<ProductEntity> findByOrderByPrice();

    List<ProductEntity> findBy(Sort sort);
}
