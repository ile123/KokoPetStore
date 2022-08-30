package com.petstore.common.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "brands")
public class Brand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(length = 40, nullable = false, unique = true)
	private String name;
	
	@Column(length = 128, nullable = false)
	private String picture;
	
	@OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Product> products;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Brand(String name, String picture, List<Product> products) {
		this.name = name;
		this.picture = picture;
		this.products = products;
	}
	
	
}
