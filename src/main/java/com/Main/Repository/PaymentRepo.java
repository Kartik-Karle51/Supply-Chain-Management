package com.Main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Main.Model.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer>{

}
