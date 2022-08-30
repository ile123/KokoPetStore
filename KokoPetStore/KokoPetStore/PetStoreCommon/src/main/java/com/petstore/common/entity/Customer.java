package com.petstore.common.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(name="first_name", length = 45, nullable = false)
	private String firstName;
	
	@Column(name="last_name", length = 45, nullable = false)
	private String lastName;
	
	@Column(length = 128, nullable = false, unique = true)
	private String email;
	
	@Column(length = 64, nullable = false)
	private String password;
	
	@Column(length = 64, nullable = false)
	private String address;
	
	@Column(length = 64, nullable = false)
	private String city;
	
	@Column(length = 64, nullable = false)
	private String country;
	
	@Column(length = 64, nullable = false)
	private String province;
	
	@Column(name="postal_code",length = 64, nullable = false)
	private String postalCode;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "customer")
	private List<CartItem> purchases = new ArrayList<CartItem>();
	
	public Customer() {
		
	}
	
	public Customer(Integer id) {
		this.Id = id;
	}

	public Customer(String firstName, String lastName, String email, String password, String address, String city,
			String country, String province, String postalCode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.address = address;
		this.city = city;
		this.country = country;
		this.province = province;
		this.postalCode = postalCode;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public List<CartItem> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<CartItem> purchases) {
		this.purchases = purchases;
	}
	
	
	
}
