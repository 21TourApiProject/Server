package com.server.tourApiProject.observePoint;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObservePointRepository extends JpaRepository<ObservePoint, Long> {
    ObservePoint findByObservePointName(@Param("observePointName")String observePointName);
}
