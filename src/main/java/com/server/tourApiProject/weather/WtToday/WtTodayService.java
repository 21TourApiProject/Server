package com.server.tourApiProject.weather.WtToday;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class WtTodayService {
    private final WtTodayRepository wtTodayRepository;

    public WtTodayParams getTodayWeatherInfo(int todayWtId) {
        WtToday wtToday = wtTodayRepository.findByTodayWtId(todayWtId);

        if (wtToday.getTodayWtId() == todayWtId) {
            WtTodayParams wtTodayParams = new WtTodayParams();
            wtTodayParams.setTodayWtName1(wtToday.getTodayWtName1());
            wtTodayParams.setTodayWtName2(wtToday.getTodayWtName2());
            return wtTodayParams;
        }
        return WtTodayParams.builder().build();
    }
}
