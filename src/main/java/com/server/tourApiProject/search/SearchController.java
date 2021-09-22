package com.server.tourApiProject.search;


import com.server.tourApiProject.myWish.MyWishParams01;
import com.server.tourApiProject.observation.ObservationService;
import com.server.tourApiProject.observation.observeFee.ObserveFee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = {"8.1 검색결과"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class SearchController {

    private final ObservationService observationService;

    @ApiOperation(value = "관측지 검색결과 ", notes = "검색어와 필터로 관측지 검색결과를 조회한다")
    @PostMapping(value = "search/observation")
    public List<SearchParams1> getObservationWithFilter(@RequestBody SearchKey searchKey){
        return observationService.getObservationWithFilter(searchKey.getFilter(), searchKey.getKeyword());
    }
}
