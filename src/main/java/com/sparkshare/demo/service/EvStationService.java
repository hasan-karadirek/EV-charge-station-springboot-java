package com.sparkshare.demo.service;

import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.stereotype.Service;
import com.sparkshare.demo.repository.EvStationRepository;
import com.sparkshare.demo.model.EvStation;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Point;
import com.sparkshare.demo.dto.CreateStationRequest;

@Service
public class EvStationService  {
    private final EvStationRepository evStationRepository;
    private final GeometryFactory geometryFactory;

    public EvStationService(EvStationRepository evStationRepository){
        this.evStationRepository=evStationRepository;
        this.geometryFactory = new GeometryFactory();
    }

    public EvStation createStation(CreateStationRequest request){
        Point location = geometryFactory.createPoint(new Coordinate(request.getLongitude(), request.getLatitude()));
        
        EvStation station = new EvStation();
        station.setAddress(request.getAddress());
        station.setLocation(location);
        station.setName(request.getName());
        station.setOwner(request.getOwner());

        return evStationRepository.save(station);
    }

}
