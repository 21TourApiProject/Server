package com.server.tourApiProject.observePoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = {"2.5 관측지"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class ObservePointController {
    private final ObservePointService observePointService;

    @ApiOperation(value = "모든 관측지 조회", notes = "모든 관측지를 조회한다")
    @GetMapping(value = "observePoints")
    public List<ObservePoint> getObservePoint(){ return observePointService.getAllObservePoint(); }

    @ApiOperation(value = "관측지 입력", notes = "관측지 정보를 입력한다")
    @PostMapping(value = "observePoint")
    public void createObserveFit(@RequestBody ObservePoint observePoint){
        observePointService.createObservePoint(observePoint);
    }
}
