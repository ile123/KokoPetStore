package com.petstore.admin.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petstore.common.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	@Query("SELECT x FROM User x WHERE x.email = :email")
	public User getUserByEmail(@Param("email") String email);
	
	@Query("SELECT x FROM User x WHERE x.firstName LIKE %?1% OR x.lastName LIKE %?1%"
			+ " OR x.email LIKE %?1%")
	public Page<User> findAll(String keyword, Pageable pageable);
}
