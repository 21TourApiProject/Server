package com.server.tourApiProject.observation;

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
public class ObservationController {
    private final ObservationService observationService;

    @ApiOperation(value = "모든 관측지 조회", notes = "모든 관측지를 조회한다")
    @GetMapping(value = "observePoints")
    public List<Observation> getObservePoint(){ return observationService.getAllObservePoint(); }

    @ApiOperation(value = "관측지 입력", notes = "관측지 정보를 입력한다")
    @PostMapping(value = "observePoint")
    public void createObserveFit(@RequestBody Observation observation){
        observationService.createObservePoint(observation);
    }
}
