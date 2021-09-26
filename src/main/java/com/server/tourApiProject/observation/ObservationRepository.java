package com.server.tourApiProject.observation;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObservationRepository extends JpaRepository<Observation, Long> {
    Observation findByObservationName(@Param("observationName")String ObservationName);

    List<Observation> findByAreaCode(@Param("areaCode") Long areaCode);

    List<Observation> findByObservationNameContainingOrOutlineContaining(String observationName, String outline);
//    반환형 findBy제목ContainingOr개요또는내용Containing(String 제목검색어, String 내용검색어);
}
