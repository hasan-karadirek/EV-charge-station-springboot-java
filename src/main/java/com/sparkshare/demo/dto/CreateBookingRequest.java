package com.sparkshare.demo.dto;
import java.time.LocalDateTime;
public class CreateBookingRequest {
    private Long station_id;
    private LocalDateTime checkin;
    private LocalDateTime checkout;
    
    public Long getStation_id() {
        return station_id;
    }
    public void setStation_id(Long station_id) {
        this.station_id = station_id;
    }
    public LocalDateTime getCheckin() {
        return checkin;
    }
    public void setCheckin(LocalDateTime checkin) {
        this.checkin = checkin;
    }
    public LocalDateTime getCheckout() {
        return checkout;
    }
    public void setCheckout(LocalDateTime checkout) {
        this.checkout = checkout;
    }

}
