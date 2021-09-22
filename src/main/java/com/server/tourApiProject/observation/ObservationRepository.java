package com.server.tourApiProject.observation;

import com.server.tourApiProject.touristPoint.touristData.TouristData;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ObservationRepository extends JpaRepository<Observation, Long> {
    Observation findByObservationName(@Param("observationName")String ObservationName);

//    List<Observation> findByAreaCode(@Param("areaCode") Long areaCode);
    //아직 areacode없음

    List<Observation> findByObservationNameContainingOrOutlineContaining(String observationName, String outline);
//    반환형 findBy제목ContainingOr개요또는내용Containing(String 제목검색어, String 내용검색어);
}
