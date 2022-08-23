package com.petstore.admin.product;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.petstore.common.entity.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>{

}
