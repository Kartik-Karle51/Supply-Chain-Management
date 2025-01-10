package com.Main.Model;

public class PaymentDTO {

	private int id;
	private double price;
	private String status;
	private int Customer_Id;
	private int Order_Id;
	private String Customer_Name;
	public PaymentDTO(int id, double price, String status, int customer_Id, int order_Id, String customer_Name) {
		super();
		this.id = id;
		this.price = price;
		this.status = status;
		Customer_Id = customer_Id;
		Order_Id = order_Id;
		Customer_Name = customer_Name;
	}
	public String getCustomer_Name() {
		return Customer_Name;
	}
	public void setCustomer_Name(String customer_Name) {
		Customer_Name = customer_Name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getCustomer_Id() {
		return Customer_Id;
	}
	public void setCustomer_Id(int customer_Id) {
		Customer_Id = customer_Id;
	}
	public int getOrder_Id() {
		return Order_Id;
	}
	public void setOrder_Id(int order_Id) {
		Order_Id = order_Id;
	}

	public PaymentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PaymentDTO [id=" + id + ", price=" + price + ", status=" + status + ", Customer_Id=" + Customer_Id
				+ ", Order_Id=" + Order_Id + ", Customer_Name=" + Customer_Name + "]";
	}
	
}
