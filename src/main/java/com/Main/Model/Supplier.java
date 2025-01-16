package com.Main.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Supplier {

	public Supplier() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int supplier_id=0;
	private long phone;
	private String supplier_name="NO Supplier";
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(mappedBy = "supplier",cascade = CascadeType.ALL)
	private List<Customer> cust;
	
	@OneToMany(mappedBy = "supplier",cascade = CascadeType.ALL)
	private List<Order> order;

	public int getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public List<Customer> getCust() {
		return cust;
	}

	public void setCust(List<Customer> cust) {
		this.cust = cust;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public Supplier(int supplier_id, long phone, String supplier_name, List<Customer> cust, List<Order> order) {
		super();
		this.supplier_id = supplier_id;
		this.phone = phone;
		this.supplier_name = supplier_name;
		this.cust = cust;
		this.order = order;
	}

	@Override
	public String toString() {
		return "Supplier [supplier_id=" + supplier_id + ", phone=" + phone + ", supplier_name=" + supplier_name
				+ ", email=" + email + ", cust=" + cust + ", order=" + order + "]";
	}
	
	
	
}
