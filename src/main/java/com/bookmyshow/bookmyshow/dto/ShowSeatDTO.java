package com.bookmyshow.bookmyshow.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeatDTO {
    private Long id;
    private SeatDTO seat;
    private Double price;
    private String status;

}
