package com.server.tourApiProject.observation;

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
@Api(tags = {"3.1 관광지"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class ObservationController {
    private final AreaService areaService;

    @ApiOperation(value = "지역코드 입력", notes = "지역코드 정보를 입력한다")
    @PostMapping(value = "area")
    public void createArea(@RequestBody List<AreaParams> areaParams){
        areaService.createArea(areaParams);
    }

}
