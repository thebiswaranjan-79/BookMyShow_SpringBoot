package com.bookmyshow.bookmyshow.controller;

import com.bookmyshow.bookmyshow.dto.BookingDTO;
import com.bookmyshow.bookmyshow.dto.BookingRequestDTO;
import com.bookmyshow.bookmyshow.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingRequestDTO bookingRequestDTO) {
        // Implementation for creating a booking
        return ResponseEntity<>(bookingService.createBooking(bookingRequestDTO), HttpStatus.CREATED);
    }
}
