package com.petstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.petstore.common.entity", "com.petstore.category", "com.petstore.product"})
public class PetStoreFrontEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetStoreFrontEndApplication.class, args);
	}

}
