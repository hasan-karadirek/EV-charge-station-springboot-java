package com.sparkshare.demo.model;

import org.locationtech.jts.geom.Point;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sparkshare.demo.util.PointSerializer;

@Entity
@Table(name = "stations")
public class EvStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String owner;

    // Geospatial field for coordinates (latitude/longitude)
    @JsonSerialize(using = PointSerializer.class)
    @Column(nullable = false)
    private Point location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
