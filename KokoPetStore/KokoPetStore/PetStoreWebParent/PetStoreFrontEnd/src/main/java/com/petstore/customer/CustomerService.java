package com.petstore.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.petstore.common.entity.CartItem;
import com.petstore.common.entity.Customer;
import com.petstore.shoppingcart.CartItemRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repo;
	
	@Autowired
	private CartItemRepository cartRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<Customer> GetAllUsers(){
		return (List<Customer>) repo.findAll();
	}
	
	public void save(Customer customer) {
		if(!isEmailUnique(customer.getEmail())) {
			return;
		}
		encodePassword(customer);
		repo.save(customer);
	}
	
	public void update(Customer customer) {
		encodePassword(customer);
		repo.save(customer);
	}
	
	public void delete(Integer id) {
		if(repo.findById(id).get() == null) {
			return;
		}
		repo.deleteById(id);
	}
	
	private void encodePassword(Customer customer) {
		String encodedPassword = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(encodedPassword);
	}
	
	public boolean isEmailUnique(String email) {
		Customer customerByEmail = repo.getCustomerByEmail(email);
		return customerByEmail == null;
	}
	
	public Customer findByEmail(String email) {
		return repo.getCustomerByEmail(email);
	}
	
	public Customer get(Integer id) {
		return repo.findById(id).get();
	}	
}
