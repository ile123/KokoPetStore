package com.petstore.admin.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.petstore.common.entity.Category;

@Service
public class CategoryService{
	
	@Autowired
	private CategoryRepository repo;
	
	public static final int CategoriesPerPage = 5;
	
	public List<Category> listAllCategories(){
		return (List<Category>) repo.findAll();
	}
	
	public void save(Category category) {
		boolean categoryExists = (category.getId() != null);
		if(categoryExists) {
			update(category);
		}
		else {
			if(!isCategoryNameUnique(category.getName())) {
				return;
			}
		}
		repo.save(category);
	}
	
	public void update(Category category) {
		if(!isCategoryNameUnique(category.getName())) {
			return;
		}
		Category existingCategory = repo.findById(category.getId()).get();
		if(category.getName().isEmpty()) {
			category.setName(existingCategory.getName());
		}
		//category.setPicture("/PetStoreBackEnd/src/main/resources/static/images/blank.png");
		repo.save(category);
	}
	
	public void delete(Integer id) {
		if(repo.findById(id).get() == null) {
			return;
		}
		repo.deleteById(id);
	}
	
	public boolean isCategoryNameUnique(String name) {
		Category categoryByName = repo.getCategoryByName(name);
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
