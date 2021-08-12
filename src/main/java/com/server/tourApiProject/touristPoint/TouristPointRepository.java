package com.server.tourApiProject.touristPoint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface TouristPointRepository extends JpaRepository<Area, Long> {

    Area findByAreaCode(@Param("areaCode") Long areaCode);
}
