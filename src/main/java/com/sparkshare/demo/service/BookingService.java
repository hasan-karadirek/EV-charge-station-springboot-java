package com.sparkshare.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import com.sparkshare.demo.dto.CreateBookingRequest;
import com.sparkshare.demo.exception.ApiException;
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
            throw new ApiException("Station is not available for desired time.", 400);
        }
        Optional<EvStation> station = stationRepository.findById(bookingRequest.getStation_id());
        if (station.isEmpty()) {
            throw new ApiException("There is no such a station associated with this id:" + bookingRequest.getStation_id(), 404);
        }
        Booking newBooking = new Booking();
        newBooking.setCheckin(bookingRequest.getCheckin());
        newBooking.setCheckout(bookingRequest.getCheckout());
        newBooking.setStation(station.get());
        newBooking.setUser(user);

        return bookingRepository.save(newBooking);
    }
    public Booking getBookingById(Long id) throws EntityNotFoundException{
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isEmpty()){
            throw new ApiException("There is no such a booking associated with this id:" + id, 404);
        }
        return booking.get();
    }
    public Page<Booking> getBookingsForConsumer(Integer page, Integer size, User user){
        Integer pageFromZero = page -1;
        Pageable pageable = PageRequest.of(pageFromZero,size);
        Page<Booking> bookings = bookingRepository.findAllByBookingUserId(user.getId(),pageable);
        return bookings;
    }
    public Page<Booking> getBookingsForStation(Integer page, Integer size,User user){
        Integer pageFromZero = page -1;
        Pageable pageable = PageRequest.of(pageFromZero,size);
        Page<Booking> bookings = bookingRepository.findAllByStationUserId(user.getId(),pageable);
        return bookings;
    }
}
