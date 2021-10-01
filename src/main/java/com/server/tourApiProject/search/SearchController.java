package com.server.tourApiProject.search;

import com.server.tourApiProject.observation.ObservationService;
import com.server.tourApiProject.touristPoint.touristData.TouristDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(tags = {"8.1 검색결과"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class SearchController {

    private final ObservationService observationService;
    private final TouristDataService touristDataService;

    @ApiOperation(value = "관측지 검색결과 ", notes = "검색어와 필터로 관측지 검색결과를 조회한다")
    @PostMapping(value = "search/observation")
    public List<SearchParams1> getObservationWithFilter(@RequestBody SearchKey searchKey){
        return observationService.getObservationWithFilter(searchKey.getFilter(), searchKey.getKeyword());
    }

    @ApiOperation(value = "관광지 검색결과 ", notes = "검색어와 필터로 관광지 검색결과를 조회한다")
    @PostMapping(value = "search/touristPoint")
    public List<SearchParams2> getTouristPointWithFilter(@RequestBody SearchKey searchKey){
        return touristDataService.getTouristPointWithFilter(searchKey.getFilter(), searchKey.getKeyword());
    }
}
