package com.server.tourApiProject.star.constellation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConstellationRepository extends JpaRepository<Constellation, Long> {
    List<Constellation> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(@Param("startDate") String startDate, @Param("endDate") String endDate);
    List<Constellation> findByConstId(@Param("constId") Long constId);
}

