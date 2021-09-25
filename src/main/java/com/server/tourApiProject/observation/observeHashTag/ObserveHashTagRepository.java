package com.server.tourApiProject.observation.observeHashTag;

import com.server.tourApiProject.touristPoint.touristDataHashTag.TouristDataHashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObserveHashTagRepository extends JpaRepository<ObserveHashTag, Long> {
    List<ObserveHashTag> findByObservationId(@Param("observationId") Long observationId);
    List<ObserveHashTag> findByHashTagId(@Param("hashTagId") Long hashTagId);
}
