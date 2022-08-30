package com.petstore.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.petstore.common.entity.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer>{
	@Query("SELECT x FROM Customer x WHERE x.email = :email")
	public Customer getCustomerByEmail(@Param("email") String email);
	
	@Query("SELECT x FROM Customer x WHERE x.firstName LIKE %?1% OR x.lastName LIKE %?1%"
			+ " OR x.email LIKE %?1%")
	public Page<Customer> findAll(String keyword, Pageable pageable);
}
