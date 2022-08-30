package com.petstore.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.petstore.common.entity","com.petstore.admin.user","com.petstore.admin.category", "com.petstore.admin.product",
	"com.petstore.admin.brand"})
public class PetStoreBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetStoreBackEndApplication.class, args);
	}
}
