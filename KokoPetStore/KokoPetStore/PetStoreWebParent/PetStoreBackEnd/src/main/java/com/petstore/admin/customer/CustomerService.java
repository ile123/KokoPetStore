package com.petstore.admin.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.petstore.admin.shoppingcart.CartItemRepository;
import com.petstore.common.entity.CartItem;
import com.petstore.common.entity.Customer;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repo;
	
	@Autowired
	private CartItemRepository cartRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public static final int CustomersPerPage = 5;
	
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
	
	public Customer get(Integer id) {
		return repo.findById(id).get();
	}
	
	public Page<Customer> listByPage(int pageNum, String sortField, String sortDir, String keyword){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNum - 1, CustomersPerPage, sort);
		
		if(keyword != null) {
			return repo.findAll(keyword,pageable);
		}
		
		return repo.findAll(pageable);
	}
	
	public Customer findByEmail(String email) {
		return repo.getCustomerByEmail(email);
	}
}
