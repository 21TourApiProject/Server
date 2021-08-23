package com.server.tourApiProject.touristPoint.touristData;

import com.server.tourApiProject.touristPoint.contentType.ContentTypeController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = {"5.3 관광지-관광 정보"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class TouristDataController {

    private final TouristDataService touristDataService;

    @ApiOperation(value = "관광 정보 입력", notes = "관광 정보를 입력한다")
    @PostMapping(value = "touristData/touristSpot")
    public List<Double> createTouristData(@RequestBody TouristData touristData){
        return touristDataService.createTouristData(touristData);
    }

    @ApiOperation(value = "관광 정보 삭제", notes = "모든 관광 정보를 삭제한다")
    @DeleteMapping(value = "touristData/")
    public void deleteTouristData(){
        touristDataService.deleteTouristData();
    }

    @ApiOperation(value = "관광지 타입 조회", notes = "관광지 타입를 조회한다")
    @GetMapping(value = "touristData/contentType/{contentId}")
    public Long getContentType(@PathVariable("contentId") Long contentId){
        return touristDataService.getContentType(contentId);
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

    @ApiOperation(value = "관광지 아이디 조회", notes = "모든 관광지의 아이디 정보를 조회한다")
    @GetMapping(value = "touristData/touristPoint/contentId")
    public List<Long> getTouristPointId(){
        return touristDataService.getTouristPointId();
    }

    @ApiOperation(value = "음식 아이디 조회", notes = "모든 음식의 아이디 정보를 조회한다")
    @GetMapping(value = "touristData/food/contentId")
    public List<Long> getFoodId(){
        return touristDataService.getFoodId();
    }

//    @ApiOperation(value = "관광지 조회", notes = "관광지를 조회한다")
//    @GetMapping(value = "touristData/{contentId}")
//    public TouristData getTouristData(@PathVariable("contentId") Long contentId){
//        return touristDataService.getTouristData(contentId);
//    }

}
