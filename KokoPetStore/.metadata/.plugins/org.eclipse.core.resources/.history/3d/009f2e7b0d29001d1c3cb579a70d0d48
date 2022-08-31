package com.petstore.admin.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.petstore.common.entity.Brand;
import com.petstore.common.entity.Category;

@Service
public class CategoryService{
	
	@Autowired
	private CategoryRepository repo;
	
	public static final int CategoriesPerPage = 5;
	
	public List<Category> GetAllCategories(){
		return (List<Category>) repo.findAll();
	}
	
	public void save(Category category) {
		if(!isCategoryNameUnique(category.getName())) {
			return;
		}
		repo.save(category);
	}
	
	public void update(Category category) {
		/*
		if(!isBrandNameUnique(brand.getName())) {
			return;
		}
		Brand existingBrand = repo.findById(brand.getId()).get();
		if(brand.getName().isEmpty()) {
			brand.setName(existingBrand.getName());
		}
		*/
		//brand.setPicture("/PetStoreBackEnd/src/main/resources/static/images/blank.png");
		repo.updateCategory(category.getName(), category.getId());
	}

	public void delete(Integer id) {
		if(repo.findById(id).get() == null) {
			return;
		}
		repo.deleteById(id);
	}
	
	public boolean isCategoryNameUnique(String name) {
		var categoryByName = repo.getCategoryByName(name);
		return categoryByName == null;
	}
	
	public Category get(Integer id) {
		return repo.findById(id).get();
	}
	
	public Page<Category> listByPage(int pageNum, String sortField, String sortDir, String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum - 1, CategoriesPerPage, sort);
		
		if(keyword != null) {
			return repo.findAll(keyword,pageable);
		}
		
		return repo.findAll(pageable);
	}
}
