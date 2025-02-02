package com.codingshuttle.jpaTutorial.jpaTuts.controllers;

import com.codingshuttle.jpaTutorial.jpaTuts.entities.ProductEntity;
import com.codingshuttle.jpaTutorial.jpaTuts.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    // for learning purpose we are not following MVC here right now (not creating DTO, Service,etc)
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    @GetMapping
//    public List<ProductEntity> getAllProducts(){
//        return productRepository.findByOrderByPrice();
//    }

//    @GetMapping
//    public List<ProductEntity> getAllProducts(@RequestParam(defaultValue = "id")String sortBy){
//
//        // By default ascending order (http://localhost:8080/products?sortBy=title)
////        return productRepository.findBy(Sort.by(sortBy));
//
//        //Return in descending order
////        return productRepository.findBy(Sort.by(Sort.Direction.DESC, sortBy));
//
////        return productRepository.findBy(Sort.by(
////                Sort.Order.asc(sortBy),
////                Sort.Order.desc("price")
////        ));
//    }

    // PAGINATION
        // http://localhost:8080/products?sortBy=quantity&pageNumber=3

    private final int PAGE_SIZE = 5;
    @GetMapping
    public Page<ProductEntity> getAllProducts(
            @RequestParam(defaultValue = "id")String sortBy,
            @RequestParam(defaultValue = "0")Integer pageNumber
            ){

        Pageable pageable = PageRequest.of(
                pageNumber,
                PAGE_SIZE,
                Sort.by(sortBy));
        return productRepository.findAll(pageable);

    }

}
