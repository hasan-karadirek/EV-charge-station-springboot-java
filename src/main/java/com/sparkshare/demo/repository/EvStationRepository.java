package com.sparkshare.demo.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sparkshare.demo.model.EvStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EvStationRepository extends JpaRepository<EvStation, Long> {
    @Query(value = "SELECT *, ST_Distance(s.location, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)) as distance FROM stations s WHERE ST_DWithin(s.location, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326), :radius) ORDER BY distance", nativeQuery = true)
    Page<EvStation> findStationsWithinRadius(@Param("longitude") double longitude, @Param("latitude") double latitude, @Param("radius") double radius, Pageable pageable);

}
