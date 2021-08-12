package com.server.tourApiProject.touristPoint.area;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = {"5.1 관광지-지역 분류"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class AreaController {
    private final AreaService areaService;

    @ApiOperation(value = "시군구 입력", notes = "해당 지역의 시군구 정보를 입력한다")
    @PostMapping(value = "area")
    public void createArea(@RequestBody AreaParams areaParams){
        areaService.createArea(areaParams);
    }

}
