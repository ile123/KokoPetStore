package com.petstore.admin.brand;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.petstore.common.entity.Brand;

public interface BrandRepository extends PagingAndSortingRepository<Brand, Integer>{

}
