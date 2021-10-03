package com.server.tourApiProject.weather.WtToday;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = {"7.2 날씨 - 오늘의 날씨 이름 조회"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class WtTodayController {
    private final WtTodayService wtTodayService;

    @ApiOperation(value = "id로 날씨 이름 조회", notes = "id로 해당 날씨의 정보를 조회한다")
    @GetMapping(value = "wtToday/{todayWtId}")
    public WtTodayParams getTodayWeatherInfo(@PathVariable("todayWtId") int todayWtId) {
        return wtTodayService.getTodayWeatherInfo(todayWtId);
    }
}
