package com.sparkshare.demo.controller;

import com.sparkshare.demo.model.EvStation;
import com.sparkshare.demo.model.User;
import com.sparkshare.demo.service.EvStationService;
import com.sparkshare.demo.service.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sparkshare.demo.dto.CreateStationRequest;
import java.util.Optional;
import java.util.List;

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
        Optional<User> user = userService.getUserByUsername(username);
        EvStation savedStation = evStationService.createStation(request,user.get());
        return ResponseEntity.ok(savedStation);

    }
    @GetMapping("/{id}")
    public ResponseEntity<EvStation> getStationById(@PathVariable Long id){
        Optional<EvStation> station = evStationService.getStationById(id);
        return station.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/all")
    public ResponseEntity<List<EvStation>> getAllStations(){
        List<EvStation> stations = evStationService.getAllStations();
        return ResponseEntity.ok(stations);
    }

    
    
    
}
