package com.server.tourApiProject.touristPoint.touristData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
