package com.Main.Model;

public class CustomerDTO {

	private String name;
	private String email;
	private String address;
	private Long phone;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public CustomerDTO(String name, String email, String address, Long phone) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
	}
	public CustomerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CustomerDTO [name=" + name + ", email=" + email + ", address=" + address + ", phone=" + phone + "]";
	}
	
}
