package com.Main.Model;

public class ProductDTO {

	private int id;
	private String name;
	private int quantity;
	private int price;
	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", qty=" + quantity + ", price=" + price + "]";
	}
	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDTO(int id, String name, int qty, int price) {
		super();
		this.id = id;
		this.name = name;
		quantity = qty;
		this.price = price;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQty(int quantity ) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
