package com.server.tourApiProject.constellation;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstellationRepository extends JpaRepository<Constellation, Long> {
   // Constellation findByConstellationName(@Param("constellationName") String ConstellationName);
}

