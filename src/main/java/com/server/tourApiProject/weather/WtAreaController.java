package com.server.tourApiProject.weather;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = {"7.1 날씨 - 지역 경도위도 조회"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class WtAreaController {
    private final WtAreaService wtAreaService;

    @ApiOperation(value = "지역명으로 경도,위도 조회", notes = "해당 지역의 경도, 위도 정보를 조회한다")
    @GetMapping(value = "wtAreas/{cityName}/{provName}")
    public WtAreaParams getAreaInfo(@PathVariable("cityName") String cityName, @PathVariable("provName") String provName) {
        return wtAreaService.getAreaInfo(cityName, provName);
    }

}
