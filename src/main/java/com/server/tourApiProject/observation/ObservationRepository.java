package com.server.tourApiProject.observation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ObservationRepository extends JpaRepository<Area, Long> {

    Area findByAreaCode(@Param("areaCode") Long areaCode);
}
