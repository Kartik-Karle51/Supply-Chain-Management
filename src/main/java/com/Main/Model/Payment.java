package com.Main.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private double price;
	private String status;
	
	
	public Payment(int id, double price, String status, Order order, Customer cust) {
		super();
		this.id = id;
		this.price = price;
		this.status = status;
		this.order = order;
		this.cust = cust;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToOne
	private Order order;
	
	@Override
	public String toString() {
		return "Payment [id=" + id + ", price=" + price + ", status=" + status + ", order=" + order + ", cust=" + cust
				+ "]";
	}

	

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	@ManyToOne
	private Customer cust;
	
}
