package com.Main.Model;

public class SupplierDTO {

	private long phone;
	private String supplier_name;
	private String email;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public SupplierDTO(long phone, String supplier_name, String email) {
		super();
		this.phone = phone;
		this.supplier_name = supplier_name;
		this.email = email;
	}
	public SupplierDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SupplierDTO [phone=" + phone + ", supplier_name=" + supplier_name + ", email=" + email + "]";
	}
	
}
