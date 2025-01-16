package com.Main.Model;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;



@Entity
public class Customer {

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Cust_Id;
	 @NotNull(message = "Customer name cannot be null")
private String name;
private String address;
private long phone;
@NotNull(message = "Email cannot be null")
@Email(message = "Email should be valid")
private String email;

@OneToMany(mappedBy = "cust",cascade = CascadeType.ALL)
private List<Payment> payment;


public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

@OneToMany(mappedBy = "cust" ,cascade = CascadeType.ALL)
private List<Order> order;

@ManyToOne
@JoinColumn(name = "supplier_id")
private Supplier supplier;


//@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
//AllUsers user;


public Customer(int cust_Id, @NotNull(message = "Customer name cannot be null") String name, String address, long phone,
		@NotNull(message = "Email cannot be null") @Email(message = "Email should be valid") String email,
		List<Payment> payment, List<Order> order, Supplier supplier) {
	super();
	Cust_Id = cust_Id;
	this.name = name;
	this.address = address;
	this.phone = phone;
	this.email = email;
	this.payment = payment;
	this.order = order;
	this.supplier = supplier;
//	this.user = user;
}
public List<Payment> getPayment() {
	return payment;
}
public void setPayment(List<Payment> payment) {
	this.payment = payment;
}
//public AllUsers getUser() {
//	return user;
//}
//public void setUser(AllUsers user) {
//	this.user = user;
//}
public Supplier getSupplier() {
	return supplier;
}
public void setSupplier(Supplier supplier) {
	this.supplier = supplier;
}


public List<Order> getOrder() {
	return order;
}
public void setOrder(List<Order> order) {
	this.order = order;
}
public int getCust_Id() {
	return Cust_Id;
}
public void setCust_Id(int cust_Id) {
	Cust_Id = cust_Id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public long getPhone() {
	return phone;
}
public void setPhone(long phone) {
	this.phone = phone;
}

@Override
public String toString() {
	return "Customer [Cust_Id=" + Cust_Id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email="
			+ email + ", payment=" + payment + ", order=" + order + ", supplier=" + supplier +"]";
}



}
