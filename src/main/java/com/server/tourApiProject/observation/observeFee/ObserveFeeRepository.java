package com.server.tourApiProject.observation.observeFee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObserveFeeRepository extends JpaRepository<ObserveFee, Long> {
    List<ObserveFee> findByObservationId(@Param("observationId") Long observationId);
}
