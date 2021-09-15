package com.server.tourApiProject.constellation;

import com.server.tourApiProject.bigPost.postHashTag.PostHashTag;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ConstellationRepository extends JpaRepository<Constellation, Long> {
    List<Constellation> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(@Param("startDate") String startDate, @Param("endDate") String endDate);
    List<Constellation> findByConstId(@Param("constId") Long constId);
}

