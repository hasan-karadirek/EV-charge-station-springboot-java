package com.sparkshare.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparkshare.demo.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    
}
