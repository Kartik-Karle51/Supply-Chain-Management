package com.Main.Model;

public class ProductDTO {

	private int id;
	private String name;
	private int qty;
	private int price;
	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", qty=" + qty + ", price=" + price + "]";
	}
	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDTO(int id, String name, int qty, int price) {
		super();
		this.id = id;
		this.name = name;
		this.qty = qty;
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
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
