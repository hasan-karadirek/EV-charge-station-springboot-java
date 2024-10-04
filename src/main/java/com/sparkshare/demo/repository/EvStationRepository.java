package com.sparkshare.demo.repository;

import com.sparkshare.demo.model.EvStation;
import org.locationtech.jts.geom.Point;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EvStationRepository extends JpaRepository<EvStation, Long> {
    @Query(value = "SELECT * FROM stations s WHERE ST_DWithin(s.location, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326), :radius)", nativeQuery = true)
    List<EvStation> findStationsWithinRadius(@Param("longitude") double longitude, @Param("latitude") double latitude, @Param("radius") double radius);

}
