package com.bookmyshow.bookmyshow.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String transactionId;

    @Column(nullable = false)
    private  Double amount;

    @Column(nullable = false)
    private LocalDateTime paymentTime;

    @Column(nullable = false)
    private  String paymentMethod; // e.g., CREDIT_CARD, DEBIT_CARD, UPI, WALLET

    @Column(nullable = false)
    private String paymentStatus; // e.g., SUCCESS, FAILED, PENDING

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private Booking booking;



}
