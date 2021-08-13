package com.server.tourApiProject.constellation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = {"6.1 별자리"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class ConstellationController {
    private final ConstellationService constellationService;

    @ApiOperation(value = "별자리 입력", notes = "별자리 정보를 입력한다.")
    @PostMapping(value = "constellation")
    public void createConstellation(@RequestBody Constellation constellation){
        constellationService.createConstellation(constellation);
    }

    @ApiOperation(value = "모든 별자리 조회", notes = "모든 별자리를 조회한다")
    @GetMapping(value = "constellations")
    public List<Constellation> getConstellation(){
        return constellationService.getAllConstellation();
    }
}
