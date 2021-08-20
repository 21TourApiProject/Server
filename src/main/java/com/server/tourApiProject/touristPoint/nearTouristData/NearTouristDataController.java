package com.server.tourApiProject.touristPoint.nearTouristData;

import com.server.tourApiProject.touristPoint.touristData.TouristData;
import com.server.tourApiProject.touristPoint.touristData.TouristDataParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = {"5.4 관광지-주변 관광 정보"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class NearTouristDataController {

    private final NearTouristDataService nearTouristDataService;

    @ApiOperation(value = "주변 관광지 정보 입력", notes = "주변 관광지 정보를 입력한다")
    @PostMapping(value = "nearTouristData/{contentId1}/{contentId2}")
    public void createNearTouristData(@PathVariable("contentId1") Long contentId1, @PathVariable("contentId2") Long contentId2){
        nearTouristDataService.createNearTouristData(contentId1, contentId2);
    }

    @ApiOperation(value = "주변 관광지 정보 조회", notes = "주변 관광지 정보를 조회한다")
    @GetMapping(value = "nearTouristData/{contentId}")
    public List<NearTouristDataParams> getNearTouristPointData(@PathVariable("contentId") Long contentId){
        return nearTouristDataService.getNearTouristData(contentId);
    }
}
