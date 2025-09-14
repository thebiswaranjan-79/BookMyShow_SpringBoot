package com.bookmyshow.bookmyshow.repositories;

import com.bookmyshow.bookmyshow.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

        List<Booking> getBookingsByUserId(Long userId);

        Optional<Booking> findByBookingNumber(Long bookingNumber);

        List<Booking> findByShowId(Long showId);
}
