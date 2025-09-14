package com.bookmyshow.bookmyshow.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private Long id;
    private String transactionId;
    private Double amount;
    private String paymentMethod; // e.g., "Credit Card", "PayPal"
    private String status; // e.g., "SUCCESS", "FAILED"
    private LocalDateTime paymentTime;

}
