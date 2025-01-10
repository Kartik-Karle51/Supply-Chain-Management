package com.Main.Exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class Global_Exception_Handler {

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
		public ResponseEntity<String> httpMethodTypeNotSupported(){
			return new ResponseEntity<String>("Method Type Not Supported ",HttpStatus.BAD_REQUEST);
		}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> httpMethodNotSupported(){
		return new ResponseEntity<String>("Please Check your request type ",HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> noSuchElementFound(){
		return new ResponseEntity<String>("Sorry..But Record is Not Found in Database",HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(EmptyFieldException.class)
	public ResponseEntity<String> emptyTextfield(){
		return new ResponseEntity<String>("Sorry..But fields can not be empty Check all fields again",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SupplierNotFoundException.class)
	public ResponseEntity<String> wrongSupplierId(){
		return new ResponseEntity<String>("Sorry..But Supplier with this Id is not present in the System",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<String> orderNotFound(){
		return new ResponseEntity<String>("Sorry..But Something went wrong May be Order with this Id is not present in the System",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> wrongCustomerId(){
		return new ResponseEntity<String>("Sorry..But Customer with this Id is not present in the System",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OrderAlreadyDeliveredException.class)
	public ResponseEntity<String> orderAlreadyDelivered(){
		return new ResponseEntity<String>("Sorry..But this order is already delivered and you can't update it now",HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(InsufficientQuantityException.class)
	public ResponseEntity<String> insufficientQuantity(){
		return new ResponseEntity<String>("Sorry..But we don't have that much quantity. Please reduce quantity of product from your order.",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PasswordMismatchException.class)
	public ResponseEntity<String> passwordMismatch(){
		return new ResponseEntity<String>("Your Old password is not correct please enter old password correctly.",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AllOrdersDeliveredException.class)
	public ResponseEntity<String>AllOrdersDeliveredException(){
		return new ResponseEntity<String>("Dear Supplier You don't have any pending order ...Enjoy your dad",HttpStatus.OK);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String>nullPointerexception(){
		return new ResponseEntity<String>("You can't insert null values",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String>nullNameException(){
		return new ResponseEntity<String>("You can't insert null values",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoQtyException.class)
	public ResponseEntity<String>NoQtyException(){
		return new ResponseEntity<String>("Please Enter quantity more than 0",HttpStatus.BAD_REQUEST);
	}
}
