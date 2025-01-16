package com.Main.Model;



import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

@Entity
@Table(name = "AllUsersDetails")
public class AllUsers implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String password;
	private String role;
	
//	@OneToOne
//	Customer customer;
	
	
	@Override
	public String toString() {
		return "AllUsers [id=" + id + ", name=" + name + ", password=" + password + ", role=" + role +  "]";
	}
	public AllUsers(int id, String name, String password, String role, Customer customer) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.role = role;

	}

	public AllUsers() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = "ROLE_"+role;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 return Collections.singletonList(new SimpleGrantedAuthority(this.role));
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return name;
	}
	
}
