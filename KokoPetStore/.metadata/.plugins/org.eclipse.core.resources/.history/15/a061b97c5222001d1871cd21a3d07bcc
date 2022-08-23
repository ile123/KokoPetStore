package com.petstore.admin.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.petstore.common.entity.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer>{
	@Query("SELECT x FROM Category x WHERE x.name = :name")
	public Category getCategoryByName(@Param("name") String name);
	
	@Query("SELECT x FROM Category x WHERE x.name LIKE %?1%")
	public Page<Category> findAll(String keyword, Pageable pageable);
}
