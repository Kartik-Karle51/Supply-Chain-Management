package com.Main.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Main.Model.*;
import com.Main.Service.Admin_Service;
import com.Main.Service.CustomerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/customer")
//@PreAuthorize("hasRole('CUSTOMER')")
public class CustomerController {

	@Autowired
	CustomerService service;
	
	@Autowired
	Admin_Service Admin_service;
	//Order Section 
	
	
	@PostMapping("/makeOrder/{id}")
	public ResponseEntity<String> makeOrder(@RequestBody Order order,@PathVariable("id")int id){
		int id1=service.makeOrder(order,id);
		return new ResponseEntity<String>("Order created successfully...! And Order id is : "+id1,HttpStatus.OK);
	}
	
	@PutMapping("/updateOrder/{orderId}")
	public ResponseEntity<String> updateOrder(@RequestBody Order order,@PathVariable("orderId")int id){ 
		service.updateOrder(order,id);
		return new ResponseEntity<String>("Order Updated Successfully",HttpStatus.OK);
	}
	
	
	//Password Related Operation
	
	@PostMapping("/changePassword/{id}/{oldPass}/{pass}")
	public ResponseEntity<String> changePassword(@PathVariable("id")int id,@PathVariable("oldPass")String oldPass, @PathVariable("pass") String pass){
		service.changePassword(id,oldPass,pass);
		return new ResponseEntity<String>("Password Changed Successfully",HttpStatus.OK);
	}
	
	@GetMapping("/getPassword/{id}")
	public ResponseEntity<String> getPasswordFirstTime(@PathVariable("id")int id){
		return new ResponseEntity<String>("Your Password is : "+service.getPassword(id)+" Please change your password for every forthnightly because anyone can access your account",HttpStatus.OK);
	}
	
	
	//Get Supplier Details
	@GetMapping("/getSupplier/{id}")
	public  ResponseEntity<List<SupplierDTO>> getSupplierDetails1(@PathVariable("id")int id){
		List<SupplierDTO> suppliers=service.getSupplierDetails(id);
		return new ResponseEntity<>(suppliers,HttpStatus.OK);
	}
	
	//Get My Orders
	@GetMapping("/getMyOrders/{id}")
	public ResponseEntity<List<OrderDTO>>getMyOrders(@PathVariable("id")int id){
		return new ResponseEntity<>(service.getMyOrders(id),HttpStatus.OK);
	}
	
	//Payment Section
	@GetMapping("/makePayment/{orderId}")
	public ResponseEntity<String>makePayment(@PathVariable("orderId")int id){
		service.makePayment(id);
		return new ResponseEntity<String>("Thank you so much for your payment",HttpStatus.OK);

	}

	@GetMapping("/getAllProducts")
	public ResponseEntity<List<ProductDTO>> getAllProducts(){
		return new ResponseEntity<>(Admin_service.getAllProducts(),HttpStatus.OK);
	}
}
