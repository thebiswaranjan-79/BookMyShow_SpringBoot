package com.bookmyshow.bookmyshow.service;

import com.bookmyshow.bookmyshow.dto.*;
import com.bookmyshow.bookmyshow.exception.ResourceNotFoundException;
import com.bookmyshow.bookmyshow.exception.SeatUnavailableException;
import com.bookmyshow.bookmyshow.model.*;
import com.bookmyshow.bookmyshow.repositories.BookingRepository;
import com.bookmyshow.bookmyshow.repositories.ShowRepository;
import com.bookmyshow.bookmyshow.repositories.ShowSeatRepository;
import com.bookmyshow.bookmyshow.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookingService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @Transactional
    public BookingDTO createBooking(BookingRequestDTO bookingRequestDTO) {

        User user  = userRepository.findById(bookingRequestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + bookingRequestDTO.getUserId()));

        Show show = showRepository.findById(bookingRequestDTO.getShowId())
                .obv  rElseThrow(() -> new ResourceNotFoundException("Show not found with id: " + bookingRequestDTO.getShowId()));

        List<ShowSeat> selectedSeats = showSeatRepository.findAllById(bookingRequestDTO.getSeatIds());

        for(ShowSeat seat : selectedSeats) {
            if(!"AVAILABLE".equals(seat.getStatus())) {
                throw new SeatUnavailableException("Seat with id " + seat.getId() + " is not available");
            }
            seat.setStatus("LOCKED");
        }

        showSeatRepository.saveAll(selectedSeats);

        Double totalAmount = selectedSeats.stream().mapToDouble(ShowSeat::getPrice).sum();

        Payment payment = new Payment();
        payment.setAmount(totalAmount);
        payment.setPaymentTime(LocalDateTime.now());
        payment.setPaymentMethod(bookingRequestDTO.getPaymentMethod());
        payment.setPaymentStatus("SUCCESS");
        payment.setTransactionId(UUID.randomUUID().toString());

        //Booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setBookingTime(LocalDateTime.now());
        booking.setTotalAmount(totalAmount);
        booking.setPayment(payment);
        booking.setBookingNumber(UUID.randomUUID().toString());
        booking.setStatus("CONFIRMED");
        booking.setShowSeats(selectedSeats);

        Booking savedBooking = bookingRepository.save(booking);
        for(ShowSeat seat : selectedSeats) {
            seat.setStatus("BOOKED");
            seat.setBooking(savedBooking);
        }

        showSeatRepository.saveAll(selectedSeats);
        // Convert to DTO and return

        return mapToBookingDto(savedBooking, selectedSeats);

    }

    private BookingDTO mapToBookingDto(Booking booking , List<ShowSeat> seats){
       BookingDTO bookingDTO = new BookingDTO();
       bookingDTO.setId(booking.getId());
       bookingDTO.setBookingNumber(booking.getBookingNumber());
       bookingDTO.setBookingTime(booking.getBookingTime());
       bookingDTO.setStatus(booking.getStatus());
       bookingDTO.setTotalAmount(booking.getTotalAmount());

        UserDTO userDTO = new UserDTO();
        userDTO.setId(booking.getUser().getId());
        userDTO.setName(booking.getUser().getName());
        userDTO.setEmail(booking.getUser().getEmail());
        userDTO.setPhoneNumber(bookingDTO.getUser().getPhoneNumber());
        //Set other user fields as needed
        bookingDTO.setUser(userDTO);

        ShowDTO showDTO = new ShowDTO();
        showDTO.setId(booking.getShow().getId());
        showDTO.setStartTime(booking.getShow().getStartTime());
        showDTO.setEndTime(booking.getShow().getEndTime());

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(booking.getShow().getMovie().getId());
        movieDTO.setTitle(booking.getShow().getMovie().getTitle());
        movieDTO.setDurationMins(booking.getShow().getMovie().getDurationMins());
        movieDTO.setGenre(booking.getShow().getMovie().getGenre());
        movieDTO.setLanguage(booking.getShow().getMovie().getLanguage());
        movieDTO.setDescription(booking.getShow().getMovie().getDescription());
        movieDTO.setReleaseDate(booking.getShow().getMovie().getReleaseDate());
        movieDTO.setPosterUrl(booking.getShow().getMovie().getPosterUrl());
        showDTO.setMovie(movieDTO);

        ScreenDTO screenDTO = new ScreenDTO();
        screenDTO.setId(booking.getShow().getScreen().getId());
        screenDTO.setName(booking.getShow().getScreen().getName());
        screenDTO.setTotalSeats(booking.getShow().getScreen().getTotalSeats());

        TheatreDTO theatreDTO = new TheatreDTO();
        theatreDTO.setId(booking.getShow().getScreen().getTheatre().getId());
        theatreDTO.setName(booking.getShow().getScreen().getTheatre().getName());
        theatreDTO.setAddress(booking.getShow().getScreen().getTheatre().getAddress());
        theatreDTO.setCity(booking.getShow().getScreen().getTheatre().getCity());
        theatreDTO.setTotalScreens(booking.getShow().getScreen().getTheatre().getTotalScreens());

        screenDTO.setTheatre(theatreDTO);
        showDTO.setScreen(screenDTO);
        bookingDTO.setShow(showDTO);


        List<ShowSeatDTO> seatDtos = seats.stream().map(seat -> {
            ShowSeatDTO seatDTO = new ShowSeatDTO();
            seatDTO.setId(seat.getId());
            seatDTO.setPrice(seat.getPrice());
            seatDTO.setStatus(seat.getStatus());

            SeatDTO seatInfoDTO = new SeatDTO();
            seatInfoDTO.setId(seat.getSeat().getId());
            seatInfoDTO.setSeatNumber(seat.getSeat().getSeatNumber());
            seatInfoDTO.setSeatType(seat.getSeat().getSeatType());
            seatInfoDTO.setBasePrice(seat.getSeat().getBaseprice());
            seatDTO.setSeat(seatInfoDTO);
            return seatDTO;
        }).toList();

        bookingDTO.setSeats(seatDtos);

        if(booking.getPayment() != null) {
            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setId(booking.getPayment().getId());
            paymentDTO.setAmount(booking.getPayment().getAmount());
            paymentDTO.setPaymentTime(booking.getPayment().getPaymentTime());
            paymentDTO.setPaymentMethod(booking.getPayment().getPaymentMethod());
            paymentDTO.setStatus(booking.getPayment().getPaymentStatus());
            paymentDTO.setTransactionId(booking.getPayment().getTransactionId());
            bookingDTO.setPayment(paymentDTO);
        }

        return bookingDTO;

    }
}
