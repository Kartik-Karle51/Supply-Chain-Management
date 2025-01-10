package com.Main.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Main.Model.Customer;
import com.Main.Model.Supplier;

public interface SupplierRepo extends JpaRepository<Supplier, Integer> {
	
}
