package com.server.tourApiProject.touristPoint.area;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AreaRepository extends JpaRepository<Area, Long> {

    Area findByAreaCode(@Param("areaCode") Long areaCode);
}
