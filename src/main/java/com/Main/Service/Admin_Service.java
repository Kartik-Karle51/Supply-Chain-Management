package com.Main.Service;


import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.Main.Exception.*;
import com.Main.Model.*;
import com.Main.Repository.*;

@Service
public class Admin_Service {

	@Autowired
	SupplierRepo s_repo;

	@Autowired
	ProductRepo p_repo;

	@Autowired
	OrderRepo o_repo;

	@Autowired
	UserRepository u_repo;

	@Autowired
	CustomerRepo c_repo;

	
	@Autowired
	PaymentRepo pay_repo;
	
	@Autowired
	SendEmail email;
	
//	@Autowired
//    private PasswordEncoder passwordEncoder;
	
	//For Generating Password
	public String generatePassword() {
		
		return UUID.randomUUID().toString().substring(0,8);
	}
	
	
	//For Sending Mail
	public void sendEmail(String mail,String password) {
		String body="Your One Time Password is "+password+" You can change it by your side after you log in the system.";
		String subject="One Time Password For the SCM";
		email.sendEmail(mail,subject,body);
	}
	
	
	// Supplier Section

	public Supplier addSupplier(Supplier supplier) {
		AllUsers user=new AllUsers();
		if (supplier.getSupplier_name().equals(null) || supplier.getSupplier_name().isEmpty()) {
			throw new EmptyFieldException();
		}
		String password=generatePassword();
	user.setName(supplier.getSupplier_name());
	user.setPassword(password);
	user.setRole("SUPPLIER");
	sendEmail(supplier.getEmail(),password);
	u_repo.save(user);
		return s_repo.save(supplier);
	}

	public void updateSupplier(int id, Supplier supplier) {
		Supplier s=s_repo.findById(id).orElseThrow(()->new SupplierNotFoundException());
		s.setEmail(supplier.getEmail());
		s.setSupplier_name(supplier.getSupplier_name());
		s.setPhone(supplier.getPhone());
		s_repo.save(s);
	}

	
	public Supplier getSupplierById(int id) {
		return s_repo.findById(id).orElseThrow(() -> new SupplierNotFoundException());
	}

	public List<Supplier> getAllSuppliers() {
		return s_repo.findAll();
	}

	public void removeSupplierById(int id) {
		s_repo.deleteById(id);
	}

	public void removeAllSuppliers() {
		s_repo.deleteAll();
	}

	
	
	
	// Order Section

	public List<OrderDTO> getAllOrders() {
	    List<Order> orders = o_repo.findAll();
	    
	    return orders.stream()
	        .map(order -> {
	            // Convert the products to ProductDTO
	            List<ProductDTO> productDTOs = order.getProd().stream()
	                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getQuantity(), product.getPrice()))
	                .collect(Collectors.toList());
	            
	            // Create and return the OrderDTO with the products
	            return new OrderDTO(
	                order.getOrder_id(),
	                order.getCust().getCust_Id(),
	                order.getCust().getName(),
	                order.getSupplier() != null ? order.getSupplier().getSupplier_name() : "no supplier",
	                order.getStatus(),
	                order.getPayment().getStatus(),
	                productDTOs
	            );
	        })
	        .collect(Collectors.toList());
	}


	public Order getOrderById(int id) {
		return o_repo.findById(id).orElseThrow(() -> new OrderNotFoundException());
	}

	public void assignOrder(int id,int orderId) {
		Supplier s = s_repo.findById(id).orElseThrow(() -> new SupplierNotFoundException());
		Order order = o_repo.findById(orderId).get();
		if(! order.getStatus().equals("Pending")) {
			new OrderAlreadyDeliveredException();
		}
		Order o = o_repo.findById(order.getOrder_id()).orElseThrow(() -> new OrderNotFoundException());
		o.setSupplier(s);
		int i=o.getCust().getCust_Id();
		Customer c=c_repo.findById(i).get();
		c.setSupplier(s);
		c_repo.save(c);
		o_repo.save(o);
	}

	public void removeOrderById(int id) {
		o_repo.deleteById(id);
	}

	public void removeAllOrders() {
		o_repo.deleteAll();
	}

	
	
	
	
	//Customer Section
	
	public void removeAllCustomers() {
			c_repo.deleteAll();
	}

	public void removeCustomerById(int id) {
		c_repo.deleteById(id);
	}

	public List<Customer> getAllCustomers() {
		return c_repo.findAll();
	}

	public Customer getCustomerById(int id) {
		return c_repo.findById(id).orElseThrow(() -> new CustomerNotFoundException());
	}

	public void updateCustomer(int id,Customer cust) {
		Customer c=c_repo.findById(id).orElseThrow(()->new CustomerNotFoundException());
		c.setAddress(cust.getAddress());
		c.setEmail(cust.getEmail());
		c.setName(cust.getName());
		c.setPhone(cust.getPhone());
		c_repo.save(c);
	}
	
	public void addCustomer(Customer customer) {
		String password=generatePassword();
		AllUsers user=new AllUsers();
		if (customer.getName().isEmpty()||customer.getName().equals(null)||customer.getName().length()==0) {
			 new EmptyFieldException();
		}
		user.setName(customer.getName());
		user.setPassword(password);
		user.setRole("CUSTOMER");
		sendEmail(customer.getEmail(),password);
//		user.setCustomer(customer);
		u_repo.save(user);
		 c_repo.save(customer);
	}

	
	
	
	//Product Section
	
	public List<ProductDTO> getAllProducts() {
		List<Product> products=p_repo.findAll();
		return products.stream()
				.map(product->new ProductDTO(product.getId(), product.getName(), product.getQuantity(), product.getPrice()))
				.collect(Collectors.toList());
	}

	public Product getProductById(int id) {
		return p_repo.findById(id).get();
	}
	
	public void addNewProduct(Product prod) {
		if(prod.getName().equals(null)||prod.getName().isEmpty()) {
			new EmptyFieldException();
		}
		p_repo.save(prod);
	}
	
	public void updateProduct(int id, Product prod) {
		Product p=p_repo.findById(id).get();
		p.setName(prod.getName());
		p.setPrice(prod.getPrice());
		p.setQuantity(prod.getQuantity());
		p_repo.save(p);
	}
	
	//Users
	public List<AllUsers> getAllUsers(){
		return u_repo.findAll();
	}


	
//	Payment Section

	public List<PaymentDTO> getAllPayments() {
		List<Payment>payment= pay_repo.findAll();
		return payment.stream()
				.map(pay->new PaymentDTO(pay.getId(), pay.getPrice(), pay.getStatus(), pay.getCust().getCust_Id(), pay.getOrder().getOrder_id(),pay.getCust().getName()))
				.collect(Collectors.toList());
	}

	public PaymentDTO getPaymentById(int id) {
		Payment payment= pay_repo.findById(id).get();
		return new PaymentDTO(payment.getId(), payment.getPrice(), payment.getStatus(), payment.getCust().getCust_Id(), payment.getOrder().getOrder_id(),payment.getCust().getName());
	}


	
	

}
