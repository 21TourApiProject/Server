package com.server.tourApiProject.observation;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservationRepository extends JpaRepository<Observation, Long> {
    Observation findByObservationName(@Param("observationName")String ObservationName);
}
