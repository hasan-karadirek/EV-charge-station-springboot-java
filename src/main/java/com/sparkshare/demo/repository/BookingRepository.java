package com.sparkshare.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparkshare.demo.model.Booking;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query(value = "SELECT * FROM bookings WHERE station_id = :id AND checkin < :checkout AND checkout > :checkin;", nativeQuery = true)
    List<Booking> findOverlappingBookings(@Param("id") Long id, @Param("checkin") LocalDateTime checkin, @Param("checkout") LocalDateTime checkout);
}
