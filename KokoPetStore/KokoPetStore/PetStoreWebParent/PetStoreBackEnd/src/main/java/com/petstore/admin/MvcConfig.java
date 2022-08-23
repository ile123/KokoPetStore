package com.petstore.admin;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.*;

public class MvcConfig implements WebMvcConfigurer {

	//namisti ovo za ostale stvari jer korisnici nece imati forografiju
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String dirName="promini kasnije";
		
		Path userPhotoDir = Paths.get(dirName);
		
		String userPhotosPath = userPhotoDir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + userPhotosPath + "/");
	}

}
