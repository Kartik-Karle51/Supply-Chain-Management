package com.Main.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Main.Model.Customer;
import com.Main.Model.Order;
import com.Main.Model.Supplier;

public interface OrderRepo extends JpaRepository<Order, Integer> {
	  @Query("SELECT o FROM Order o WHERE o.cust.Cust_Id = :customerId")
	    List<Order> findSupplierByCustomerId(@Param("customerId") int customerId);

	    // Correct query to find all orders associated with a supplier based on supplier ID
	    @Query("SELECT o FROM Order o WHERE o.supplier.supplier_id = :supplierId")
	    List<Order> findOrdersBySupplierId(@Param("supplierId") int supplierId);

@Query("SELECT o FROM Order o WHERE o.cust.Cust_Id = :customerId")
List<Order> findOrderrsByCustomerId(@Param("customerId") int customerId);
}
