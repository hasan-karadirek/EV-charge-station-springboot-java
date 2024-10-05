package com.sparkshare.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sparkshare.demo.model.Booking;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query(value = "SELECT * FROM bookings WHERE station_id = :id AND checkin < :checkout AND checkout > :checkin;", nativeQuery = true)
    List<Booking> findOverlappingBookings(@Param("id") Long id, @Param("checkin") LocalDateTime checkin, @Param("checkout") LocalDateTime checkout);

    @Query("SELECT b FROM Booking b WHERE b.station.user.id = :userId")
    Page<Booking> findAllByStationUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("SELECT b FROM Booking b WHERE b.user.id = :userId")
    Page<Booking> findAllByBookingUserId(@Param("userId") Long userId, Pageable pageable);
}
