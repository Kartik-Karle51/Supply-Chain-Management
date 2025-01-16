package com.Main.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Main.Exception.CustomerNotFoundException;
import com.Main.Exception.InsufficientQuantityException;
import com.Main.Exception.NoQtyException;
import com.Main.Exception.OrderAlreadyDeliveredException;
import com.Main.Exception.OrderNotFoundException;
import com.Main.Exception.PasswordMismatchException;
import com.Main.Model.*;
import com.Main.Repository.*;

@Service
public class CustomerService {

	@Autowired
	ProductRepo p_repo;

	@Autowired
	OrderRepo o_repo;

	@Autowired
	UserRepository u_repo;
	
	@Autowired
	CustomerRepo c_repo;
	
	@Autowired
	PaymentRepo paymentRepo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	//For Changing Password
	public void changePassword(int id,String oldPass,String pass) {
		AllUsers user=u_repo.findById(id).get();
		if(!user.getPassword().equals(oldPass)) {
			new PasswordMismatchException();
		}
		user.setPassword(passwordEncoder.encode(pass));
		u_repo.save(user);
	}

	
	//For Getting paassword first time
	public String getPassword(int id) {
		return u_repo.findById(id).get().getPassword();
	}
	
	
	//Checking product's availability in the system
//	public boolean available(Order order) {
//		Product prod= order.getProd();
//		int id=prod.getId();
//		Product p=p_repo.findById(id).get();
//		if(prod.getQuantity()>p.getQuantity()) {
//			new InsufficientQuantityException();
//		}
//		return true;
//	}
//	
	
	
	public boolean available(Order order) {
	    List<Product> products = order.getProd();  // This is a List<Product>
	    if (products == null || products.isEmpty()) {
	        throw new IllegalArgumentException("Order has no products");
	    }

	    for (Product prod : products) {
	        int id = prod.getId();
	        Product p = p_repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

	        if (prod.getQuantity() > p.getQuantity()) {
	            throw new InsufficientQuantityException();
	        }
	        if(prod.getQuantity()==0) {
	        	throw new NoQtyException();
	        }
	    }

	    return true;
	}

	
//	public void updateQty(Order order) {
//	    List<Product> products = order.getProd();  // This is a List<Product>
//
//	    for (Product prod : products) {
//	        int id = prod.getId();
//	        // Fetch the product from the repository
//	        Product p = p_repo.findById(id).orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
//
//	        int originalQty = p.getQuantity();
//	        int orderQty = prod.getQuantity();
//
//	        // Ensure there is enough stock to reduce
//	        int newQty = originalQty - orderQty;
//	        
//	        // Check if the stock is sufficient
//	        if (newQty < 0) {
//	            throw new InsufficientQuantityException();
//	        }
//
//	        // Update the product's quantity and save it back to the repository
//	        p.setQuantity(newQty);
//	        p_repo.save(p);
//	    }
//	}


	
	
//	public int makeOrder(Order order,int id) {
//		 if (available(order)) {
//	  Customer c=c_repo.findById(id).get();
//	  double price=0;
//	  List<Product>products=order.getProd();
//	  for(Product prod:products) {
//		  price+=prod.getQuantity()*prod.getPrice();
//          System.out.println(prod.getQuantity());
//	  }
//	  Payment pay=new Payment();
//	  pay.setCust(c);
//	  pay.setOrder(order);
//	  pay.setPrice(price);
//	  pay.setStatus("Pending");
//	 
//	    	order.setProd(order.getProd());
//	    	order.setDate(LocalDate.now());
//	    	order.setCust(c);
//	        o_repo.save(order);
//	        paymentRepo.save(pay);
//	 	   
//	        o_repo.findById(order.getOrder_id()).get().setStatus("Pending");
//	        updateQty(order);
//	        return o_repo.findById(order.getOrder_id()).get().getOrder_id();
//	    } else {
//	        throw new InsufficientQuantityException();
//	    }
//	}
//
//
//	
//	
//	public void updateOrder(Order order,int id) {
//	Order o=o_repo.findById(id).orElseThrow(() -> new OrderNotFoundException());
//	if(o.getStatus().toLowerCase().equals("delivered"));
//	{
//		new OrderAlreadyDeliveredException();
//	}
//	
//	if(available(order)) {
//		double price=0;
//		  List<Product>products=order.getProd();
//		  for(Product prod:products) {
//			  price+=prod.getQuantity()*prod.getPrice();
//		  }
//		  Payment pay=o.getPayment();
//		  pay.setOrder(o);
//		  pay.setPrice(price);
//		  pay.setStatus("Pending");
//			 paymentRepo.save(pay);
//	o.setProd(order.getProd());
//	o.setDate(LocalDate.now());
//	o_repo.save(o);
//
//	updateQty(order);}
//	}

	public Product updateQty(Product prod, int orderQuantity) {
	    // Fetch the product from the repository
	    Product p = p_repo.findById(prod.getId()).orElseThrow(() -> new RuntimeException("Product with id " + prod.getId() + " not found"));

	    int originalQty = p.getQuantity();

	    // Ensure there is enough stock to reduce
	    int newQty = originalQty - orderQuantity;
	    System.out.println(prod.getName()+" "+newQty);
	    // Check if the stock is sufficient
	    if (newQty < 0) {
	        throw new InsufficientQuantityException();
	    }

	    // Update the product's quantity and save it back to the repository
	    p.setQuantity(newQty);
	   return p;
	}

	
	
	@Transactional
	public int makeOrder(Order order, int customerId) {
	    if (available(order)) {
	        Customer c = c_repo.findById(customerId).orElseThrow(() -> new CustomerNotFoundException());
	        double price = 0;
	        List<Product> products = order.getProd();
	        
	        // Calculate price and track quantities for order
	        for (Product prod : products) {
	            price += prod.getQuantity() * prod.getPrice();
	            // Ensure product quantities are updated correctly based on the order
	            // Assuming updateQty adjusts the product stock quantity correctly
	            p_repo.save(updateQty(prod, prod.getQuantity()));
	        }

	        Payment pay = new Payment();
	        pay.setCust(c);
	        pay.setOrder(order);
	        pay.setPrice(price);
	        pay.setStatus("Pending");

	        order.setDate(LocalDate.now());
	        order.setCust(c);

	        // Save the order and payment
	        o_repo.save(order);
	        paymentRepo.save(pay);

	        // Optional: Update order status if needed
	        o_repo.findById(order.getOrder_id()).ifPresent(o -> {
	            o.setStatus("Pending");
	            o_repo.save(o);
	        });

	        return order.getOrder_id();
	    } else {
	        throw new InsufficientQuantityException();
	    }
	}

	@Transactional
	public void updateOrder(Order order, int id) {
	    Order existingOrder = o_repo.findById(id).orElseThrow(() -> new OrderNotFoundException());

	    if (existingOrder.getStatus().toLowerCase().equals("delivered")) {
	        throw new OrderAlreadyDeliveredException();
	    }

	    if (available(order)) {
	        double price = 0;
	        List<Product> products = order.getProd();

	        // Calculate price and adjust product quantities
	        for (Product prod : products) {
	            price += prod.getQuantity() * prod.getPrice();
	            // Update the stock quantity based on the new order details
	           p_repo.save(updateQty(prod, prod.getQuantity()));
	        }

	        Payment pay = existingOrder.getPayment();
	        pay.setOrder(existingOrder);
	        pay.setPrice(price);
	        pay.setStatus("Pending");
	        paymentRepo.save(pay);

	        // Update the existing order with new details
	        existingOrder.setProd(products);
	        existingOrder.setDate(LocalDate.now());
	        o_repo.save(existingOrder);

	     
	    }
	}

	
	
	
	
	//Get SupplierDetails

	public List<SupplierDTO> getSupplierDetails(int id) {
		List<Order> orders= o_repo.findSupplierByCustomerId(id);
		List<Order>pendingOrders=orders.stream()
				.filter(order->order.getStatus().equals("Pending"))
				.collect(Collectors.toList());
		List<Supplier> suppliers=pendingOrders.stream()
				.map(supplier->supplier.getSupplier())
				.collect(Collectors.toList());
		for(Supplier s:suppliers) {
			System.out.println("gfhffhf"+s.getSupplier_name());
		}
		
		return suppliers.stream()
				.map(supplier->new SupplierDTO(supplier.getPhone(), supplier.getSupplier_name(), supplier.getEmail()))
				.collect(Collectors.toList());
	}





	public List<OrderDTO> getMyOrders(int id) {
	    List<Order> orders = o_repo.findOrderrsByCustomerId(id);
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
	                order.getPayment().getStatus()+" Price: "+order.getPayment().getPrice(),
	                productDTOs
	            );
	        })
	        .collect(Collectors.toList());
	}




	public void makePayment(int id) {
		Payment p=o_repo.findById(id).get().getPayment();
		p.setStatus("Paid");
		paymentRepo.save(p);
	}


	
	

}
