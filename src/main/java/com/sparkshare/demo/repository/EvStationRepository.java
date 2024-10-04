package com.sparkshare.demo.repository;

import com.sparkshare.demo.model.EvStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvStationRepository extends JpaRepository<EvStation, Long> {
    
}
