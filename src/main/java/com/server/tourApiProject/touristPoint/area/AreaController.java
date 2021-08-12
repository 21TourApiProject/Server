package com.server.tourApiProject.touristPoint.area;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = {"3.1 관광지-지역분류"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class AreaController {
    private final AreaService areaService;

    @ApiOperation(value = "지역 입력", notes = "지역 정보를 입력한다")
    @PostMapping(value = "area")
    public void createArea(@RequestBody List<AreaParams> areaParams){
        areaService.createArea(areaParams);
    }

    @ApiOperation(value = "지역코드 조회", notes = "모든 지역코드 정보를 조회한다")
    @GetMapping(value = "areaCode/all")
    public List<Long> getAllAreaCode(){
        return areaService.getAreaCode();
    }

    @ApiOperation(value = "시군구 입력", notes = "해당 지역의 시군구 정보를 입력한다")
    @PostMapping(value = "sigungu/{areaCode}/{areaName}")
    public void createSigungu(@PathVariable("areaCode") Long areaCode, @PathVariable("areaName") String areaName, @RequestBody List<AreaParams> sigunguParams){
        areaService.createSigungu(areaCode, areaName, sigunguParams);
    }

}
