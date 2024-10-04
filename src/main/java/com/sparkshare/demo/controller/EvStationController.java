package com.sparkshare.demo.controller;

import com.sparkshare.demo.model.EvStation;
import com.sparkshare.demo.service.EvStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sparkshare.demo.dto.CreateStationRequest;

@RestController
@RequestMapping("/api/station/")
public class EvStationController {

    private final EvStationService evStationService;

    @Autowired
    public EvStationController(EvStationService evStationService){
        this.evStationService = evStationService;
    }

    @PostMapping("/")
    public ResponseEntity<EvStation> createEvStation(@RequestBody CreateStationRequest request){
        EvStation savedStation = evStationService.createStation(request);
        return ResponseEntity.ok(savedStation);

    }
    
}
