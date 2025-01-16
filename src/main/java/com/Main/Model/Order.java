package com.Main.Model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "Orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Order_id;
	private String status;
	private LocalDate date;
	

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "Supplier_Id")
	private Supplier supplier;
	
	@OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
	private Payment payment;


	public Order(int order_id, String status, LocalDate date, Supplier supplier, Payment payment, List<Product> prod,
			Customer cust) {
		super();
		Order_id = order_id;
		this.status = status;
		this.date = date;
		this.supplier = supplier;
		this.payment = payment;
		this.prod = prod;
		this.cust = cust;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	@ManyToMany(cascade = CascadeType.MERGE)    
	private List<Product>prod;

	@ManyToOne

	@JoinColumn(name = "Customer_Id")
	private Customer cust;
	
	

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}
	
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getOrder_id() {
		return Order_id;
	}

	public void setOrder_id(int order_id) {
		Order_id = order_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	
	public List<Product> getProd() {
		return prod;
	}

	public void setProd(List<Product> prod) {
		this.prod = prod;
	}


	@Override
	public String toString() {
		return "Order [Order_id=" + Order_id + ", status=" + status + ", date=" + date + ", supplier=" + supplier
				+ ", payment=" + payment + ", prod=" + prod + ", cust=" + cust + "]";
	}
	
	
}
