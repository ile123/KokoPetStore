package com.petstore.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.petstore.common.entity.Role;
import com.petstore.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUserRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userTest = new User("pero.hero@gmail.com","PeroTheHero","Pero","Peric");
		userTest.setRole(roleAdmin);
		
		User savedUser = repo.save(userTest);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	
	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}
	
	@Test
	public void testGetUserById() {
		User tempUser = repo.findById(1).get();
		System.out.println(tempUser);
		assertThat(tempUser).isNotNull();
	}
	
	@Test
	public void testUpdateUserDetails() {
		User tempUser = repo.findById(1).get();
		tempUser.setEmail("testing");
		
		repo.save(tempUser);
	}
	
	
	@Test
	public void testDeleteUser() {
		Integer userId = 1;
		repo.deleteById(userId);
	}
}
