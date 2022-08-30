package com.petstore.admin.brand;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.petstore.admin.FileUploadUtil;
import com.petstore.common.entity.Brand;

@Controller
public class BrandController {
	
	@Autowired
	private BrandService service;
	
	@GetMapping("/brands")
	public String listFirstPage(Model model) {
		return listByPage(1, model, "name", "asc", null);
	}
	
	@GetMapping("/brands/new")
	public String NewBrand(Model model) {
		Brand brand = new Brand();
		model.addAttribute("brand",brand);
		model.addAttribute("pageTitle","Create New Brand");
		return "brand/brand_form";
	}
	
	@PostMapping("/brands/save")
	public String saveBrand(Brand brand, @RequestParam("image") MultipartFile multipartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		if(fileName.isEmpty() || fileName == null) {
			brand.setPicture("/PetStoreBackEnd/src/main/resources/images/blank.png");
		}
		else {
			brand.setPicture(fileName);
		}
		service.save(brand);
		Brand savedBrand= service.GetAllBrands()
				.stream()
				.filter(temp -> brand.getName().equals(temp.getName()))
				.findAny().orElse(null);
		String uploadDir = "../brand-images/" + savedBrand.getId();
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		return "redirect:/brands";
	}
	
	@GetMapping("/brands/edit/{id}")
	public String editBrand(@PathVariable(name = "id") Integer id, Model model) {
		Brand brand = service.get(id);
		if(brand == null) {
			return "redirect:/brands";
		}
		model.addAttribute("brand",brand);
		model.addAttribute("pageTitle","Edit Brand(ID: " + id + ")");
		return "brand/brand_form";
	}
	
	@GetMapping("/brands/delete/{id}")
	public String deleteBrand(@PathVariable(name = "id") Integer id, Model model) {
		service.delete(id);
		return "redirect:/brands";
	}
	
	@GetMapping("/brands/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir,
			@Param("keyword") String keyword) {
		Page<Brand> page = service.listByPage(pageNum, sortField, sortDir, keyword);
		List<Brand> listBrands = page.getContent();
		long startCount = (pageNum -1) * BrandService.BrandsPerPage + 1;
		long endCount = startCount + BrandService.BrandsPerPage - 1;
		if(endCount> page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());
		model.addAttribute("listBrands", listBrands);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("keyword", keyword);
		return "brand/brands";
	}
}
