package com.Main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Main.Model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

}
