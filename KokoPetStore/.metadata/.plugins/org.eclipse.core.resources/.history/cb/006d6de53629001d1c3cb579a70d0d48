package com.petstore.admin.product;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.petstore.admin.FileUploadUtil;
import com.petstore.admin.brand.BrandService;
import com.petstore.admin.category.CategoryService;
import com.petstore.common.entity.Brand;
import com.petstore.common.entity.Category;
import com.petstore.common.entity.Product;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BrandService brandService;
	
	@GetMapping("/products")
	public String listFirstPage(Model model) {
		return listByPage(1, model, "name", "asc", null);
	}
	
	@GetMapping("/products/new")
	public String NewProduct(Model model) {
		Product product = new Product();
		List<Category> listCategories= categoryService.GetAllCategories();
		List<Brand> listBrands = brandService.GetAllBrands();
		model.addAttribute("product",product);
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("listBrands", listBrands);
		model.addAttribute("pageTitle","Create New Product");
		return "product/product_form";
	}
	
	@PostMapping("/products/save")
	public String saveProduct(Product product, @RequestParam("image") MultipartFile multipartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		if(fileName.isEmpty() || fileName == null) {
			product.setPicture("/PetStoreBackEnd/src/main/resources/images/blank.png");
		}
		else {
			product.setPicture(fileName);
		}
		productService.save(product);
		var savedCategory= productService.GetAllProducts()
				.stream()
				.filter(temp -> product.getName().equals(temp.getName()))
				.findAny().orElse(null);
		String uploadDir = "../product-images/" + savedCategory.getId();
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		return "redirect:/products";
	}
	
	@PutMapping("/products/update")
	public String updateProduct(@ModelAttribute("product") Product product,@RequestParam(value = "id", required = true) Integer Id) {
		product.setPicture("/PetStoreBackEnd/src/main/resources/images/blank.png");
		var tempProduct = productService.get(Id);
		tempProduct.setName(product.getName());
		tempProduct.setShortDescription(product.getShortDescription());
		tempProduct.setDescription(product.getDescription());
		tempProduct.setPicture(product.getPicture());
		tempProduct.setPrice(product.getPrice());
		tempProduct.setBrand(product.getBrand());
		tempProduct.setCategory(product.getCategory());
		productService.update(product);
		return "redirect:/products";
	}
	
	@DeleteMapping("/products/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Integer id) {
		productService.delete(id);
		return "redirect:/products";
	}
	
	@GetMapping("/products/edit/{id}")
	public String editProduct(@PathVariable(name = "id") Integer id, Model model) {
		Product product = productService.get(id);
		List<Category> listCategories= categoryService.GetAllCategories();
		List<Brand> listBrands = brandService.GetAllBrands();
		if(product == null) {
			return "redirect:/products";
		}
		model.addAttribute("product",product);
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("listBrands", listBrands);
		model.addAttribute("pageTitle","Edit Product(ID: " + id + ")");
		return "product/updateProduct";
	}
	
	
	@GetMapping("/products/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir,
			@Param("keyword") String keyword) {
		Page<Product> page = productService.listByPage(pageNum, sortField, sortDir, keyword);
		List<Product> listProducts = page.getContent();
		long startCount = (pageNum -1) * productService.ProductsPerPage + 1;
		long endCount = startCount + productService.ProductsPerPage - 1;
		if(endCount> page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());
		model.addAttribute("listProducts", listProducts);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", reverseSortDir);
		model.addAttribute("keyword", keyword);
		return "product/products";
	}
}
