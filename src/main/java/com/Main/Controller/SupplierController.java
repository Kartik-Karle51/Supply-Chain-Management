package com.Main.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.Main.Model.Customer;
import com.Main.Model.CustomerDTO;
import com.Main.Model.Order;
import com.Main.Model.OrderDTO;
import com.Main.Service.SupplierService;

@RestController
@RequestMapping("/supplier")
//@PreAuthorize("hasRole('SUPPLIER')")
public class SupplierController {

	@Autowired
	SupplierService service;
	
	

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
	
	
	//Order
	
	@GetMapping("/checkMyOrder/{id}")
	public ResponseEntity<List<OrderDTO>> checkMyOrder(@PathVariable("id")int id){
		return new ResponseEntity<>(service.checkMyOrder(id),HttpStatus.OK);
	}
	
	@PutMapping("/changeStatus/{orderId}/{status}")
	public ResponseEntity<String> changeStatus(@PathVariable("orderId")int orderId,@PathVariable("status")String status){
		service.changeStatus(orderId,status);
		return new ResponseEntity<String>("The status of order is changed.Thank you for your support",HttpStatus.OK);
	}
	
	
	//Customer
	@GetMapping("/checkCustomer/{id}")
	public ResponseEntity<CustomerDTO> checkCustomer(@PathVariable("id")int id){
		return new ResponseEntity<>(service.checkCustomer(id),HttpStatus.OK);
	}
}
