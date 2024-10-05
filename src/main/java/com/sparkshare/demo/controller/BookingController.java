package com.sparkshare.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
