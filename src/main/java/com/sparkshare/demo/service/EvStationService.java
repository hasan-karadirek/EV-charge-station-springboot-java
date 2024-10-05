package com.sparkshare.demo.service;

import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.stereotype.Service;
import com.sparkshare.demo.repository.EvStationRepository;
import com.sparkshare.demo.model.EvStation;
import com.sparkshare.demo.model.User;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Point;
import com.sparkshare.demo.dto.CreateStationRequest;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class EvStationService  {
    private final EvStationRepository evStationRepository;
    private final GeometryFactory geometryFactory;

    public EvStationService(EvStationRepository evStationRepository){
        this.evStationRepository=evStationRepository;
        this.geometryFactory = new GeometryFactory();
    }

    public EvStation createStation(CreateStationRequest request, User user){
        Point location = geometryFactory.createPoint(new Coordinate(request.getLongitude(), request.getLatitude()));
        
        EvStation station = new EvStation();
        station.setAddress(request.getAddress());
        station.setLocation(location);
        station.setName(request.getName());
        station.setOwner(request.getOwner());
        station.setUser(user);

        return evStationRepository.save(station);
    }
    public Optional<EvStation> getStationById(Long id){
        return evStationRepository.findById(id);
    }
    public Page<EvStation> getAllStations(double latitude, double longitude, double radius, Integer page, Integer size){
        Integer pageFromZero = page -1;
        Pageable pageable = PageRequest.of(pageFromZero,size);
        return evStationRepository.findStationsWithinRadius(longitude,latitude,radius,pageable);
    }

}
