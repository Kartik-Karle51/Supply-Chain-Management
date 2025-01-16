package com.Main.Service;


import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public void sendEmail(String mail,String password,int user_id,int id) {
		String body="Your One Time Password is "+password+" And Your user Id is : "+user_id+" You can change it by your side after you log in the system using this user id.And Your main Id is :"+id+" This id is for accessing our services like making order and other services.";
		String subject="One Time Password For the SCM";
		email.sendEmail(mail,subject,body);
	}
	
	
	// Supplier Section

	public Supplier addSupplier(Supplier supplier) {
		AllUsers user=new AllUsers();
		
		List<Supplier> AllSupplier=s_repo.findAll();
		for(Supplier sup:AllSupplier) {
			if(sup.getSupplier_name().equals(supplier.getSupplier_name())&& sup.getEmail().equals(supplier.getEmail())&& sup.getPhone()==supplier.getPhone()) {
				throw new SupplierAlreadyExistsException();
				
			}
		}
		if (supplier.getSupplier_name().equals(null) || supplier.getSupplier_name().isEmpty()) {
			throw new EmptyFieldException();
		}
		String password=generatePassword();
	user.setName(supplier.getSupplier_name());
	user.setPassword(password);
	user.setRole("SUPPLIER");
	u_repo.save(user);
	Supplier s=s_repo.save(supplier);
	sendEmail(supplier.getEmail(),password,user.getId(),supplier.getSupplier_id());
	
		return s;
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

//	public void removeSupplierById(int id) {
//		s_repo.deleteById(id);
//	}

	public void removeAllSuppliers() {
		s_repo.deleteAll();
	}

	
	
	
	// Order Section

//	public List<OrderDTO> getAllOrders() {
//	    List<Order> orders = o_repo.findAll();
//	    
//	    return orders.stream()
//	        .map(order -> {
//	            // Convert the products to ProductDTO
//	            List<ProductDTO> productDTOs = order.getProd().stream()
//	                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getQuantity(), product.getPrice()))
//	                .collect(Collectors.toList());
//	            System.out.println(productDTOs);
//	            // Create and return the OrderDTO with the products
//	            return new OrderDTO(
//	                order.getOrder_id(),
//	                order.getCust().getCust_Id(),
//	                order.getCust().getName(),
//	                order.getSupplier() != null ? order.getSupplier().getSupplier_name() : "no supplier",
//	                order.getStatus(),
//	                order.getPayment().getStatus(),
//	                productDTOs
//	            );
//	        })
//	        .collect(Collectors.toList());
//	}
	@Transactional
	public List<OrderDTO> getAllOrders() {
	    List<Order> orders = o_repo.findAll();

	    return orders.stream()
	        .map(order -> {
	            // Convert the products to ProductDTO
	            List<ProductDTO> productDTOs = order.getProd().stream()
	                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getQuantity(), product.getPrice()))
	                .collect(Collectors.toList());
	            System.out.println(productDTOs);
	            // Create and return the OrderDTO with the products
	            return new OrderDTO(
	                order.getOrder_id(),
	                order.getCust().getCust_Id(),
	                order.getCust().getName(),
	                order.getSupplier() != null ? order.getSupplier().getSupplier_name() : "no supplier",
	                order.getStatus(),
	                order.getPayment() != null ? order.getPayment().getStatus() : "no payment",  // Handle null payments
	                productDTOs
	            );
	        })
	        .collect(Collectors.toList());
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

//	public void removeOrderById(int id) {
//		o_repo.deleteById(id);
//	}

	public void removeAllOrders() {
		List<Order>orders=o_repo.findAll();
		for(Order ord:orders) {
			Order order=o_repo.findById(ord.getOrder_id()).get();
			order.setCust(null);
			order.setPayment(null);
			order.setSupplier(null);
			order.setProd(null);
			o_repo.save(order);
			o_repo.deleteById(order.getOrder_id());
		}
		
	}

	
	public OrderDTO getOrderById(int id) {
		Order o= o_repo.findById(id).orElseThrow(() -> new OrderNotFoundException());
	List<ProductDTO>products=o.getProd().stream()
			.map(prod->new ProductDTO(prod.getId(), prod.getName(), prod.getQuantity(), prod.getPrice()))
			.collect(Collectors.toList());
	return new OrderDTO(o.getOrder_id(), 
			o.getCust().getCust_Id(),
			o.getCust().getName(), 
			o.getSupplier()!= null ? o.getSupplier().getSupplier_name() : "no supplier",
			o.getStatus(), o.getPayment().getStatus(),
			products);	}
	
	
	//Customer Section
	
	public void removeAllCustomers() {
			c_repo.deleteAll();
	}

//	public void removeCustomerById(int id) {
//		c_repo.deleteById(id);
//	}

	public List<CustomerDTO> getAllCustomers() {
		List<Customer>customers= c_repo.findAll();
		return customers.stream()
				.map(customer->new CustomerDTO(customer.getName(), customer.getEmail(), customer.getAddress(), customer.getPhone()))
				.collect(Collectors.toList());
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
		List<Customer> AllCust=c_repo.findAll();
		for(Customer cust:AllCust) {
			if(cust.getName().equals(customer.getName()) && cust.getEmail().equals(customer.getEmail()) && cust.getPhone()==customer.getPhone()){
				throw new CustomerAlreadyExistsException();
				
			}
		}
		AllUsers user=new AllUsers();
		if (customer.getName().isEmpty()||customer.getName().equals(null)||customer.getName().length()==0) {
			 new EmptyFieldException();
		}
		user.setName(customer.getName());
		user.setPassword(password);
		user.setRole("CUSTOMER");
		u_repo.save(user);
		 c_repo.save(customer);
		sendEmail(customer.getEmail(),password,user.getId(),customer.getCust_Id());
//		user.setCustomer(customer);
		
	
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


//	public void removeAllProducts() {
//		p_repo.deleteAll();
//	}
//

//	@Transactional
//	public void removeAllProducts() {
//	    // Ensure the orders are persisted
//	    List<Order> orders = o_repo.findAll();  // Fetch orders first if not already persisted
//
//	    for (Order order : orders) {
//	        // Ensure the products are removed from the order or handled properly
//	        for (Product product : order.getProd()) {
//	            // Handle your deletion logic, e.g. remove the product from the order
//	            p_repo.delete(product);  // Deleting individual product
//	        }
//	        
//	        // Optionally remove the relationship between Order and Product if necessary
//	        order.getProd().clear();  // Remove products from the order
//	        o_repo.save(order);  // Save the updated order
//
//	        // Delete the order if necessary
//	        o_repo.delete(order);  // Only delete the order if you intend to delete it as well
//	    }
//	}
//	
//	public void removeProductById(int id) {
//		p_repo.deleteById(id);
//	}


	public void removeUserById(int id) {
	u_repo.deleteById(id);
		
	}
	
	public void removeAllUsers() {
		u_repo.deleteAll();
	}

	
	
	public void removeSupplierById(int id) {
	    Supplier supplier = s_repo.findById(id).orElseThrow(() -> new SupplierNotFoundException());

	    // Clear associations in related entities (Orders, Customers, etc.)
	    for (Order order : supplier.getOrder()) {
	        order.setSupplier(null);  // Remove supplier reference from order
	        o_repo.save(order);  // Save the updated order
	    }
	    for (Customer customer : supplier.getCust()) {
	        customer.setSupplier(null);  // Remove supplier reference from customer
	        c_repo.save(customer);  // Save the updated customer
	    }
	    
	    s_repo.deleteById(id);  // Delete the supplier
	}

	@Transactional
	public void removeOrderById(int id) {
	    Order order = o_repo.findById(id).orElseThrow(() -> new OrderNotFoundException());

	    // Clear associations
	    order.setSupplier(null);  // Remove supplier reference from order
	    order.setCust(null);  // Remove customer reference from order
	    order.setPayment(null);  // Remove payment reference from order

	    // Remove product associations and persist them
	    for (Product product : order.getProd()) {
	        product.getOrder().remove(order);  // Remove order from product
	        p_repo.save(product);  // Save updated product
	    }

	    o_repo.save(order);  // Save the updated order before deletion
	    o_repo.deleteById(id);  // Delete the order
	}

	@Transactional
	public void removeProductById(int id) {
	    Product product = p_repo.findById(id).orElseThrow(() -> new NoSuchElementException());

	    // Remove product from associated orders
	    for (Order order : product.getOrder()) {
	        order.getProd().remove(product);  // Remove product from order
	        o_repo.save(order);  // Save the updated order
	    }

	    p_repo.deleteById(id);  // Delete the product
	}

	@Transactional
	public void removeCustomerById(int id) {
	    Customer customer = c_repo.findById(id).orElseThrow(() -> new CustomerNotFoundException());

	    // Clear references to customer in related entities (orders, payments, etc.)
	    for (Order order : customer.getOrder()) {
	        order.setCust(null);  // Remove customer reference from order
	        o_repo.save(order);  // Save the updated order
	    }
	    for (Payment payment : customer.getPayment()) {
	        payment.setCust(null);  // Remove customer reference from payment
	        pay_repo.save(payment);  // Save the updated payment
	    }
	    
	    // If customer has a supplier reference, clear it
	    if (customer.getSupplier() != null) {
	        customer.setSupplier(null);
	        c_repo.save(customer);  // Save the updated customer
	    }

	    c_repo.deleteById(id);  // Delete the customer
	}

	@Transactional
	public void removeAllProducts() {
	    // Ensure all orders are handled before deleting products
	    List<Order> orders = o_repo.findAll();
	    for (Order order : orders) {
	        for (Product product : order.getProd()) {
	            product.getOrder().remove(order);  // Remove order reference from product
	            p_repo.save(product);  // Save the updated product
	        }
	        o_repo.save(order);  // Save the updated order
	    }

	    p_repo.deleteAll();  // Delete all products
	}

}
