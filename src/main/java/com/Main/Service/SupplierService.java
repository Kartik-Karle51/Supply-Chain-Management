package com.Main.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Main.Exception.AllOrdersDeliveredException;
import com.Main.Exception.PasswordMismatchException;
import com.Main.Model.AllUsers;
import com.Main.Model.Customer;
import com.Main.Model.CustomerDTO;
import com.Main.Model.Order;
import com.Main.Model.OrderDTO;
import com.Main.Model.ProductDTO;
import com.Main.Repository.CustomerRepo;
import com.Main.Repository.OrderRepo;
import com.Main.Repository.ProductRepo;
import com.Main.Repository.SupplierRepo;
import com.Main.Repository.UserRepository;


@Service
public class SupplierService {

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	ProductRepo p_repo;

	@Autowired
	OrderRepo o_repo;

	@Autowired
	UserRepository u_repo;
	
	@Autowired
	SupplierRepo s_repo;
	
	@Autowired
	CustomerRepo c_repo;
	
	//For Changing Password
		public void changePassword(int id,String oldPass,String pass) {
			AllUsers user=u_repo.findById(id).get();
			if(!user.getPassword().equals(oldPass)) {
				new PasswordMismatchException();
			}
			user.setPassword(passwordEncoder.encode(pass));
			u_repo.save(user);
		}

		
		//For Getting password first time
		public String getPassword(int id) {
			return u_repo.findById(id).get().getPassword();
		}


		public List<OrderDTO> checkMyOrder(int id) {
		    // Fetch orders by supplier ID
		    List<Order> orders = o_repo.findOrdersBySupplierId(id);

		    // Filter orders with status 'pending'
		    List<Order> newOrder = orders.stream()
		        .filter(order -> order.getStatus().equals("Pending")) // Filter orders with 'pending' status
		        .collect(Collectors.toList());

		    // Check if the filtered list is empty or null
		    if (newOrder.isEmpty() || newOrder.equals(null) || newOrder.size() == 0) {
		        throw new AllOrdersDeliveredException();
		    }

		    // Map orders to OrderDTO and include ProductDTO list for each order
		    return newOrder.stream()
		        .map(order -> {
		            // Convert the list of products to ProductDTO
		            List<ProductDTO> productDTOs = order.getProd().stream()
		                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getQuantity(), product.getPrice()))
		                .collect(Collectors.toList());

		            // Create and return OrderDTO with the list of ProductDTO
		            return new OrderDTO(
		                order.getOrder_id(),
		                order.getCust().getCust_Id(),
		                order.getCust().getName(),
		                order.getSupplier() != null ? order.getSupplier().getSupplier_name() : "no supplier",
		                order.getStatus(),
		                order.getPayment().getStatus(),
		                productDTOs // Add the list of ProductDTOs
		            );
		        })
		        .collect(Collectors.toList());
		}



		public CustomerDTO checkCustomer(int id) {
			Customer c=o_repo.findById(id).get().getCust();
		return new CustomerDTO(c.getName(), c.getEmail(), c.getAddress(), c.getPhone());
			
		}


		public void changeStatus(int orderId, String status) {
			Order o=o_repo.findById(orderId).get();
			o.setStatus(status);
			o_repo.save(o);
		}
		
	
}
