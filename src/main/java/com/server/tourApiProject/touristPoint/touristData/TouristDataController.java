package com.server.tourApiProject.touristPoint.touristData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = {"5.3 관광지-관광 정보"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class TouristDataController {

    private final TouristDataService touristDataService;

    @ApiOperation(value = "관광 정보 입력", notes = "관광 정보를 입력한다")
    @PostMapping(value = "touristData/touristSpot")
    public void createTouristData(@RequestBody TouristData touristData){
        touristDataService.createTouristData(touristData);
    }

    @ApiOperation(value = "관광지 정보 조회", notes = "관광지 정보를 조회한다")
    @GetMapping(value = "touristData/touristPoint/{contentId}")
    public TouristDataParams getTouristPointData(@PathVariable("contentId") Long contentId){
        return touristDataService.getTouristPointData(contentId);
    }

    @ApiOperation(value = "음식 정보 조회", notes = "음식 정보를 조회한다")
    @GetMapping(value = "touristData/food/{contentId}")
    public TouristDataParams2 getFoodData(@PathVariable("contentId") Long contentId){
        return touristDataService.getFoodData(contentId);
    }

}
