package com.sparkshare.demo.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import com.sparkshare.demo.dto.CreateBookingRequest;
import com.sparkshare.demo.model.Booking;
import com.sparkshare.demo.model.EvStation;
import com.sparkshare.demo.model.User;
import com.sparkshare.demo.repository.BookingRepository;
import com.sparkshare.demo.repository.EvStationRepository;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final EvStationRepository stationRepository;

    public BookingService(BookingRepository bookingRepository, EvStationRepository stationRepository){
        this.bookingRepository = bookingRepository;
        this.stationRepository = stationRepository;
    }

    public Booking createBooking(CreateBookingRequest bookingRequest, User user) throws EntityNotFoundException{
        List<Booking> overlappingBookings = bookingRepository.findOverlappingBookings(bookingRequest.getStation_id(), bookingRequest.getCheckin(), bookingRequest.getCheckout());
        if (overlappingBookings.size()>0){
            throw new RuntimeException("Station is not available!");
        }
        Optional<EvStation> station = stationRepository.findById(bookingRequest.getStation_id());
        if (station.isEmpty()) {
             throw new EntityNotFoundException("There is no such a station with this id: " + bookingRequest.getStation_id());
        }
        Booking newBooking = new Booking();
        newBooking.setCheckin(bookingRequest.getCheckin());
        newBooking.setCheckout(bookingRequest.getCheckout());
        newBooking.setStation(station.get());
        newBooking.setUser(user);

        return bookingRepository.save(newBooking);


    }
}
