package com.server.tourApiProject.observation.course;

import com.server.tourApiProject.observation.ObservationService;
import com.server.tourApiProject.observation.observeFee.ObserveFeeService;
import com.server.tourApiProject.observation.observeHashTag.ObserveHashTagService;
import com.server.tourApiProject.observation.observeImage.ObserveImageService;
import com.server.tourApiProject.touristPoint.touristData.TouristDataCourseParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(tags = {"4.2 관측지 코스"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor

public class CourseController {
    private final CourseService courseService;

    @ApiOperation(value = "코스 관광지 정보 조회", notes = "코스에 필요한 관광지 정보리스트를 조회한다")
    @GetMapping(value = "observation/{observationId}/courseTouristPoint")
    public List<TouristDataCourseParams> getCourseTouristPointList(@PathVariable("observationId") Long observationId){
        return courseService.getCourseTPList(observationId);
    }

    @ApiOperation(value = "코스 속 이름들 정보 조회", notes = "인디케이터에 필요한 코스 이름리스트를 조회한다")
    @GetMapping(value = "observation/{observationId}/courseNames")
    public List<String> getCourseNameList(@PathVariable("observationId") Long observationId){
        return courseService.getCourseNameList(observationId);
    }
}
