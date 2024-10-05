package com.sparkshare.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

import com.sparkshare.demo.dto.CreateBookingRequest;
import com.sparkshare.demo.model.Booking;
import com.sparkshare.demo.model.User;
import com.sparkshare.demo.service.BookingService;
import com.sparkshare.demo.service.UserService;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    
    private final BookingService bookingService;
    private final UserService userService;

    public BookingController(BookingService bookingService, UserService userService){
        this.bookingService=bookingService;
        this.userService=userService;
    }

    @PostMapping("/")
    public ResponseEntity<Booking> createBooking(@RequestBody CreateBookingRequest bookingRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> user = userService.getUserByUsername(username);
        Booking newBooking = bookingService.createBooking(bookingRequest, user.get());
        return ResponseEntity.ok(newBooking);
    }
    @GetMapping("/station")
    public ResponseEntity<Page<Booking>> getBookingsForStation(@RequestParam(defaultValue = "1") @Min(1) Integer page, @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> user = userService.getUserByUsername(username);
        Page<Booking> bookings = bookingService.getBookingsForStation(page,size,user.get());
        return ResponseEntity.ok(bookings);
    }
    @GetMapping("/consumer")
    public ResponseEntity<Page<Booking>> getBookings(@RequestParam(defaultValue = "1") @Min(1) Integer page, @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> user = userService.getUserByUsername(username);
        Page<Booking> bookings = bookingService.getBookingsForConsumer(page,size,user.get());
        return ResponseEntity.ok(bookings);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id){
        Booking booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    
}
