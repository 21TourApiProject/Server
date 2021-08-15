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
    @GetMapping(value = "observations")
    public List<Observation> getAllObservation(){ return observationService.getAllObservation(); }

    @ApiOperation(value = "관측지 입력", notes = "관측지 정보를 입력한다")
    @PostMapping(value = "observation")
    public void createObservation(@RequestBody ObservationParams observationParams){
        observationService.createObservation(observationParams);
    }

    @ApiOperation(value = "관측지 조회", notes = "관측지 id로 관측지를 조회한다")
    @GetMapping(value = "observation/{observationId}")
    public Observation getObservation(@PathVariable("observationId") Long observationId){ return observationService.getObservation(observationId); }


}
