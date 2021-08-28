package com.server.tourApiProject.touristPoint.touristData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface TouristDataRepository extends JpaRepository<TouristData, Long> {
    TouristData findByContentId(@Param("contentId") Long contentId);

    List<TouristData> findByContentTypeId(@Param("contentTypeId") Long contentTypeId);

    List<TouristData> findByIsNearAndContentTypeId(@Param("isNear") Integer isNear, @Param("contentTypeId") Long contentTypeId);

}
