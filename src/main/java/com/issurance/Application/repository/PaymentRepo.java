package com.issurance.Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.issurance.Application.entities.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {

	
}
