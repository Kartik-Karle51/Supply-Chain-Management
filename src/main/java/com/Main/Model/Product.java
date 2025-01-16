package com.Main.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product {

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	 @NotNull(message = "Price cannot be null")
	private int price;
	 @NotNull(message = "Product name cannot be null")
	private String name;
	 @NotNull(message = "Quantity cannot be null")
	private int quantity;
	

	
	@ManyToMany(mappedBy = "prod",cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.ALL})
	private List<Order> order;
	
	public Product(int id, int price, String name, int quantity,List<Order> order) {
		super();
		this.id = id;
		this.price = price;
		this.name = name;
		this.quantity = quantity;
		this.order = order;
	}
	

	public List<Order> getOrder() {
		return order;
	}
	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", price=" + price + ", name=" + name + ", quantity=" + quantity + "]";
	}
	
	
	
}
