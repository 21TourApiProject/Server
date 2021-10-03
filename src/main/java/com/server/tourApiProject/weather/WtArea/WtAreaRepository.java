package com.server.tourApiProject.weather.WtArea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface WtAreaRepository extends JpaRepository<WtArea, Long> {
    //시 이름과 구 이름으로 경도, 위도 정보 조회
    WtArea findByCityNameAndProvName(@Param("cityName") String cityName, @Param("provName") String provName);
}
