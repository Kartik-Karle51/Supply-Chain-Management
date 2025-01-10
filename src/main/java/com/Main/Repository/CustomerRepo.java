package com.Main.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Main.Model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	
}
