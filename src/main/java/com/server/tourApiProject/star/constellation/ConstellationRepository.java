package com.server.tourApiProject.star.constellation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConstellationRepository extends JpaRepository<Constellation, Long> {
    List<Constellation> findByStartDate1LessThanEqualAndEndDate1GreaterThanEqual(@Param("startDate1") String startDate1, @Param("endDate1") String endDate1);

    List<Constellation> findByStartDate2LessThanEqualAndEndDate2GreaterThanEqual(@Param("startDate2") String startDate2, @Param("endDate2") String endDate2);

}

