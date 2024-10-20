package com.iyzico.challenge.bank.repository;

import com.iyzico.challenge.bank.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
