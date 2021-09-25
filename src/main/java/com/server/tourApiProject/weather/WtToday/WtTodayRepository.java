package com.server.tourApiProject.weather.WtToday;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface WtTodayRepository extends JpaRepository<WtToday, Long> {
    //id로 오늘의 날씨 명칭 조회
    WtToday findByTodayWtId(@Param("todayWtId") String todayWtId);
}
