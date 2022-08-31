package com.petstore.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.petstore.common.entity.Customer;
@Controller
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@GetMapping("/register")
	public String displayRegistrationForm(Model model) {
		Customer customer = new Customer();
		
		model.addAttribute("pageTitle", "Customer Registration");
		model.addAttribute("customer", customer);
		
		return "customer/register_form";
	}
	
	@PostMapping("/register/new")
	public String saveUser(Customer customer) {
		service.save(customer);
		return "index";
	}
	
	@GetMapping("/customers/edit")
	public String editCustomer(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Customer customer = service.findByEmail(auth.getName());
		if(customer == null) {
			return "redirect:/";
		}
		model.addAttribute("customer",customer);
		model.addAttribute("pageTitle","Edit Customer Information");
		return "customer/customer_form";
	}
	
	@PostMapping("/customers/save")
	public String saveCustomer(Customer customer) {
		service.save(customer);
		return "redirect:/";
	}
}
