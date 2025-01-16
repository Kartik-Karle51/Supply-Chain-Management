package com.Main.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.Main.Model.*;
import com.Main.Repository.PaymentRepo;
import com.Main.Service.Admin_Service;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/admin")
//@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
	
	@Autowired
	Admin_Service service;
	
	
	
//	Supplier's Operations
	
	
	@PostMapping("/addSupplier")
    public ResponseEntity<String> addSupplier(@Valid @RequestBody Supplier supplier) {
        System.out.println("Adding Supplier: " + supplier.getSupplier_name());
        service.addSupplier(supplier);
        return new ResponseEntity<>("Supplier added", HttpStatus.OK);
    }
	
	@GetMapping("/getSupplierById/{id}")
	public ResponseEntity<Supplier> getSupplierById(@PathVariable("id") int id){
		return new ResponseEntity<Supplier>(service.getSupplierById(id),HttpStatus.OK);
	}
	
	@GetMapping("/getAllSuppliers")
	public ResponseEntity<List<Supplier>> getAllSuppliers(){
		return new ResponseEntity<>(service.getAllSuppliers(),HttpStatus.OK);
	}
	
	@PutMapping("/updateSupplier/{id}")
	public ResponseEntity<String> updateSupplier(@PathVariable("id")int id,@RequestBody Supplier supplier){
		service.updateSupplier(id,supplier);
		return new ResponseEntity<String>("Customer with id : "+id+" is updated successfully",HttpStatus.OK);
	}
	
	@DeleteMapping("/removeSupplierById/{id}")
	public ResponseEntity<String> removeSupplierById(@PathVariable("id")int id){
		service.removeSupplierById(id);
		return new ResponseEntity<String>("Supplier having id :"+id+" removed successfully!!!!"+id,HttpStatus.OK);
	}
	
	@DeleteMapping("/removeAllSuppliers")
	public ResponseEntity<String> removeAllSuppliers(){
		service.removeAllSuppliers();
		return new ResponseEntity<String>("All Suppliers removed successfully!!!!",HttpStatus.OK);
	}
	
	
	
//	Order's Operation
	
	@GetMapping("/getAllOrders")
	public ResponseEntity<List<OrderDTO>> getAllOrders(){
		return new ResponseEntity<>(service.getAllOrders(),HttpStatus.OK);
	}
	
	@GetMapping("/getOrderById/{id}")
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") int id){
		return new ResponseEntity<OrderDTO>(service.getOrderById(id),HttpStatus.OK);
	}
	
	@PostMapping("/assignOrder/{id}/{orderId}")
	public ResponseEntity<String> assignOrderToSupplier(@PathVariable("id") int id,@PathVariable("orderId")int orderId){
		service.assignOrder(id,orderId);
		return new ResponseEntity<String>("Order assigned successfully to the supplier who has id as "+id,HttpStatus.OK);
	}
	
	@DeleteMapping("/removeOrderById/{id}")
	public ResponseEntity<String> removeOrderById(@PathVariable("id")int id){
		service.removeOrderById(id);
		return new ResponseEntity<String>("Order removed successfully!!!!"+id,HttpStatus.OK);
	}
	
	@DeleteMapping("/removeAllOrders")
	public ResponseEntity<String> removeAllOrders(){
		service.removeAllOrders();
		return new ResponseEntity<String>("All Orders removed successfully!!!!",HttpStatus.OK);
	}
	
	
	
	//Customer's Operation
	
	@PostMapping("/addCustomer")
	public ResponseEntity<String> addCustomer(@Valid @RequestBody Customer customer){
		service.addCustomer(customer);
		return new ResponseEntity<String>("Customer added successfully with Id : "+customer.getCust_Id()+" Please search '/getCustomerById' to view supplier\n\n",HttpStatus.OK);
	}
	
	@GetMapping("/getCustomerById/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id){
		return new ResponseEntity<Customer>(service.getCustomerById(id),HttpStatus.OK);
	}
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<CustomerDTO>> getAllCustomers(){
		return new ResponseEntity<>(service.getAllCustomers(),HttpStatus.OK);
	}
	
	@PutMapping("/updateCustomer/{id}")
	public ResponseEntity<String> updateCustomer(@PathVariable("id")int id,@RequestBody Customer cust){
		service.updateCustomer(id,cust);
		return new ResponseEntity<String>("Customer with id : "+id+" is updated successfully",HttpStatus.OK);
	}
	
	@DeleteMapping("/removeCustomerById/{id}")
	public ResponseEntity<String> removeCustomerById(@PathVariable("id")int id){
		service.removeCustomerById(id);
		return new ResponseEntity<String>("Customer removed successfully!!!!",HttpStatus.OK);
	}
	
	@DeleteMapping("/removeAllCustomers")
	public ResponseEntity<String> removeAllCustomers(){
		service.removeAllCustomers();
		return new ResponseEntity<String>("All Customers removed successfully!!!!",HttpStatus.OK);
	}
	
	
	
	
//	Product System
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<List<ProductDTO>> getAllProducts(){
		return new ResponseEntity<>(service.getAllProducts(),HttpStatus.OK);
	}
	
	@GetMapping("/getProductById/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") int id){	
		return new ResponseEntity<Product>(service.getProductById(id),HttpStatus.OK);
	}
	
	@PostMapping("/addNewProduct")
	public ResponseEntity<String>addNewProduct(@Valid @RequestBody Product prod){
		service.addNewProduct(prod);
		return new ResponseEntity<String>("Product added successfully",HttpStatus.OK);
	}
	
	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<String>updateProduct(@RequestBody Product prod,@PathVariable("id")int id){
		service.updateProduct(id,prod);
		return new ResponseEntity<String>("Product Updated Successfully",HttpStatus.OK);
	}
	@DeleteMapping("/removeAllProducts")
	public ResponseEntity<String>removeAllProducts(){
		service.removeAllProducts();
		return new ResponseEntity<String>("Products removed successsfully",HttpStatus.OK);
	}
	
	@DeleteMapping("/removeProductById/{id}")
	public ResponseEntity<String>removeProductById(@PathVariable("id")int id){
		service.removeProductById(id);
		return new ResponseEntity<String>("Product Removed successfully",HttpStatus.OK);
	}
	
	
	//Users
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<AllUsers>> getAllUsers(){
		return new ResponseEntity<>(service.getAllUsers(),HttpStatus.OK);
	}
	
	@DeleteMapping("/removeUserById/{id}")
	public ResponseEntity<String>removeUserById(@PathVariable("id")int id){
		service.removeUserById(id);
		return new ResponseEntity<String>("User removed successsfully",HttpStatus.OK);
	}
	
	@DeleteMapping("/removeAllUsers")
	public ResponseEntity<String>removeAllUsers(){
		service.removeAllUsers();
		return new ResponseEntity<String>("All Users removed successsfully",HttpStatus.OK);
	}
	
	
	//Payment Section
	@GetMapping("/getAllPayments")
	public ResponseEntity<List<PaymentDTO>>getAllPayments(){
		return new ResponseEntity<>(service.getAllPayments(),HttpStatus.OK);
	}
	
	@GetMapping("/getPaymentById/{id}")
	public ResponseEntity<PaymentDTO>getPaymentById(@PathVariable("id")int id){
		return new ResponseEntity<PaymentDTO>(service.getPaymentById(id),HttpStatus.OK);
	}
}
