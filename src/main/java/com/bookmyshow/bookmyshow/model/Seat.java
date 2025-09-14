package com.bookmyshow.bookmyshow.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seats")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    @ManyToOne
    private String seatNumber; // e.g., "A1", "B2"

    @Column(nullable = false)
    private String seatType; // e.g., REGULAR, PREMIUM, VIP

    @Column(nullable = false)
    private Double baseprice;

    @ManyToOne
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;

}
