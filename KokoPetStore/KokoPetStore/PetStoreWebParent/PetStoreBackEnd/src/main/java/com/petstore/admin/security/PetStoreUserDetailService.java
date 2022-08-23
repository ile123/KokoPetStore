package com.petstore.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.petstore.admin.user.UserRepository;
import com.petstore.common.entity.User;

public class PetStoreUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.getUserByEmail(email);
		if(user != null) {
			return new PetStoreUserDetails(user);
		}
		throw new UsernameNotFoundException("User not found with given email" + email);
	}

}
