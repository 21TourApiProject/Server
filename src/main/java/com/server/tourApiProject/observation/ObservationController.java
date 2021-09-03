package com.server.tourApiProject.observation;

import com.server.tourApiProject.observation.course.CourseService;
import com.server.tourApiProject.observation.observeFee.ObserveFee;
import com.server.tourApiProject.observation.observeFee.ObserveFeeService;
import com.server.tourApiProject.observation.observeHashTag.ObserveHashTagService;
import com.server.tourApiProject.observation.observeImage.ObserveImageService;
import com.server.tourApiProject.touristPoint.touristData.TouristDataCourseParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = {"4.1 관측지"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class ObservationController {
    private final ObservationService observationService;
    private final ObserveHashTagService observeHashTagService;
    private final ObserveImageService observeImageService;
    private final ObserveFeeService observeFeeService;
    private final CourseService courseService;

    @ApiOperation(value = "모든 관측지 조회", notes = "모든 관측지를 조회한다")
    @GetMapping(value = "observations")
    public List<Observation> getAllObservation(){ return observationService.getAllObservation(); }

    @ApiOperation(value = "관측지 입력", notes = "관측지 정보를 입력한다")
    @PostMapping(value = "observation")
    public void createObservation(@RequestBody ObservationParams observationParams){
        observationService.createObservation(observationParams);
    }

    @ApiOperation(value = "관측지 조회", notes = "관측지 id로 관측지를 조회한다")
    @GetMapping(value = "observation/{observationId}")
    public Observation getObservation(@PathVariable("observationId") Long observationId){
        return observationService.getObservation(observationId);
    }

    @ApiOperation(value = "관측지 이미지 경로", notes = "관측지 이미지경로를 id로 조회한다")
    @GetMapping(value = "observation/{observationId}/observeImage")
    public List<String> getObserveImagePath(@PathVariable("observationId") Long observationId){
        return observeImageService.getObserveImage(observationId);
    }

    @ApiOperation(value = "관측지 해쉬태그 조회 ", notes = "관측지id로 해쉬태그를 조회한다")
    @GetMapping(value = "observation/{observationId}/observeHashTag")
    public List<String> getObserveHashTags(@PathVariable("observationId") Long observationId){
        return observeHashTagService.getObserveHashTag(observationId);
    }

    @ApiOperation(value = "관측지 입장료 조회 ", notes = "관측지id로 입장료리스트를 조회한다")
    @GetMapping(value = "observation/{observationId}/observeFee")
    public List<ObserveFee> getObserveFeeList(@PathVariable("observationId") Long observationId){
        return observeFeeService.getObserveFees(observationId);
    }

    @ApiOperation(value = "코스 관광지 정보 조회", notes = "코스에 필요한 관광지 정보리스트를 조회한다")
    @GetMapping(value = "observation/{observationId}/courseTouristPoint")
    public List<TouristDataCourseParams> getCourseTouristPointList(@PathVariable("observationId") Long observationId){
        return courseService.getCourseTPList(observationId);
    }
}
