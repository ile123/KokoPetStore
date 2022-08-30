package com.petstore.admin.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.petstore.common.entity.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>{
	
	@Query("SELECT x FROM Product x WHERE x.name = :name")
	public Product getProductByName(@Param("name") String name);
	
	@Query("SELECT x FROM Product x WHERE x.name LIKE %?1%")
	public Page<Product> findAll(String keyword, Pageable pageable);
}
