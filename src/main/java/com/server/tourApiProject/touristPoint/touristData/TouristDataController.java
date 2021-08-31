package com.server.tourApiProject.touristPoint.touristData;

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
    @PostMapping(value = "touristData/touristPoint")
    public void createTouristData(@RequestBody TouristData touristData){
        touristDataService.createTouristData(touristData);
    }

    //테스트용(나중에 삭제)
    @ApiOperation(value = "관광 정보 삭제", notes = "모든 관광 정보를 삭제한다")
    @DeleteMapping(value = "touristData/")
    public void deleteTouristData(){
        touristDataService.deleteTouristData();
    }

    //테스트용(나중에 삭제)
    @ApiOperation(value = "관광지 삭제", notes = "모든 관광지를 삭제한다")
    @DeleteMapping(value = "touristData/touristPoint")
    public void deleteTouristPoint(){
        touristDataService.deleteTouristPoint();
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

    @ApiOperation(value = "관광지 좌표 조회", notes = "모든 관광지의 좌표를 조회한다")
    @GetMapping(value = "touristData/touristPoint/map")
    public Double [][] getTouristPointMap(){
        return touristDataService.getTouristPointMap();
    }

    @ApiOperation(value = "음식 좌표 조회", notes = "모든 음식의 좌표를 조회한다")
    @GetMapping(value = "touristData/food/map")
    public Double [][] getFoodMap(){
        return touristDataService.getFoodMap();
    }

    @ApiOperation(value = "관광지 좌표 조회 2", notes = "주변 정보가 없는 관광지의 좌표를 조회한다")
    @GetMapping(value = "touristData/touristPoint/noNear/map")
    public Double [][] getTouristPointMap2(){
        return touristDataService.getTouristPointMap2();
    }

    @ApiOperation(value = "음식 좌표 조회 2", notes = "주변 정보가 없는 음식의 좌표를 조회한다")
    @GetMapping(value = "touristData/food/noNear/map")
    public Double [][] getFoodMap2(){
        return touristDataService.getFoodMap2();
    }

    @ApiOperation(value = "관광지 아이디 조회 2", notes = "주변 정보가 없는 관광지의 아이디 정보를 조회한다")
    @GetMapping(value = "touristData/touristPoint/noNear/contentId")
    public List<Long> getTouristPointId2(){
        return touristDataService.getTouristPointId2();
    }

    @ApiOperation(value = "음식 아이디 조회 2", notes = "주변 정보가 없는 음식의 아이디 정보를 조회한다")
    @GetMapping(value = "touristData/food/noNear/contentId")
    public List<Long> getFoodId2(){
        return touristDataService.getFoodId2();
    }

    @ApiOperation(value = "코스 관광지 정보 조회", notes = "코스에 필요한 관광지 정보를 조회한다")
    @GetMapping(value = "touristData/CoursetouristPoint/{contentId}")
    public TouristDataCourseParams getCourseTouristPointData(@PathVariable("contentId") Long contentId){
        return touristDataService.getCourseTouristPointData(contentId);
    }
}
