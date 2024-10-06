package com.sparkshare.demo.controller;

import com.sparkshare.demo.model.EvStation;
import com.sparkshare.demo.model.User;
import com.sparkshare.demo.service.EvStationService;
import com.sparkshare.demo.service.UserService;
import com.sparkshare.demo.dto.CreateStationRequest;

import org.springframework.data.domain.Page;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@RestController
@RequestMapping("/api/station/")
public class EvStationController {

    private final EvStationService evStationService;
    private final UserService userService;

    @Autowired
    public EvStationController(EvStationService evStationService, UserService userService){
        this.evStationService = evStationService;
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<EvStation> createEvStation(@RequestBody CreateStationRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);
        EvStation savedStation = evStationService.createStation(request,user);
        return ResponseEntity.ok(savedStation);

    }
    @GetMapping("/{id}")
    public ResponseEntity<EvStation> getStationById(@PathVariable Long id){
        return ResponseEntity.ok(evStationService.getStationById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<Page<EvStation>> getAllStations(
        @RequestParam double latitude,
        @RequestParam double longitude,
        @RequestParam double radius,
        @RequestParam(defaultValue = "1") @Min(1) Integer page,
        @RequestParam(defaultValue = "10") @Min(1) @Max(100) Integer size
    ){
        Page<EvStation> stations = evStationService.getAllStations(latitude,longitude,radius,page,size);
        return ResponseEntity.ok(stations);
    }

    
    
    
}
