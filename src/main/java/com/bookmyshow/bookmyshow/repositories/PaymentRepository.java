package com.bookmyshow.bookmyshow.repositories;

import com.bookmyshow.bookmyshow.model.Movie;
import com.bookmyshow.bookmyshow.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByTransactionId(String transactionId);





}
