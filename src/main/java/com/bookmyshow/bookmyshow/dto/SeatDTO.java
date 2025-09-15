package com.bookmyshow.bookmyshow.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatDTO {
    private  Long id;
    private String seatNumber;
    private String seatType; // e.g., "Regular", "Premium", "VIP"
    private Double basePrice;

}

