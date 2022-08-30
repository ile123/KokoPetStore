package com.petstore.category;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.petstore.common.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
	@Query("SELECT x FROM Category x WHERE x.name = :name")
	public Category getCategoryByName(@Param("name") String name);
	
	@Query("SELECT x FROM Category x WHERE x.name LIKE %?1%")
	public List<Category> findAll(String keyword);
}
