package com.sparkshare.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

import com.sparkshare.demo.dto.CreateBookingRequest;
import com.sparkshare.demo.model.Booking;
import com.sparkshare.demo.model.User;
import com.sparkshare.demo.repository.BookingRepository;
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
    public Booking createBooking(@RequestBody CreateBookingRequest bookingRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> user = userService.getUserByUsername(username);
        return bookingService.createBooking(bookingRequest, user.get());
    }
    @GetMapping("/station")
    public Page<Booking> getBookingsForStation(@RequestParam(defaultValue = "1") @Min(1) Integer page, @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> user = userService.getUserByUsername(username);
        return bookingService.getBookingsForStation(page,size,user.get());
    }
    @GetMapping("/consumer")
    public Page<Booking> getBookings(@RequestParam(defaultValue = "1") @Min(1) Integer page, @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> user = userService.getUserByUsername(username);
        return bookingService.getBookingsForConsumer(page,size,user.get());
    }
    @GetMapping("/{id}")
    public Optional<Booking> getBookingById(@PathVariable Long id){
        return bookingService.getBookingById(id);
    }

    
}
