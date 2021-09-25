package com.server.tourApiProject.touristPoint.touristDataHashTag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TouristDataHashTagRepository extends JpaRepository<TouristDataHashTag, Long> {

    List<TouristDataHashTag> findByContentId(@Param("contentId") Long contentId);
    List<TouristDataHashTag> findByHashTagId(@Param("hashTagId") Long hashTagId);
}
