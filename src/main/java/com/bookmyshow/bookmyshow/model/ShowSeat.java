package com.bookmyshow.bookmyshow.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "show_seats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private  Show show;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private  Seat seat;

    @Column(nullable = false)
    private String seatStatus; // AVAILABLE, BOOKED, BLOCKED

    @Column(nullable = false)
    private  Double price; // Price for this seat in this show

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking; // Nullable, set when booked

    @Column(nullable = false)
    private String status; // AVAILABLE, BOOKED, BLOCKED
}
