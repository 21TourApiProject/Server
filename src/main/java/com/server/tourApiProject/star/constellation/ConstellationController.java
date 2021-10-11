package com.server.tourApiProject.star.constellation;

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
    public void createConstellation(@RequestBody Constellation constellation) {
        constellationService.createConstellation(constellation);
    }

    @ApiOperation(value = "모든 별자리 조회", notes = "모든 별자리를 조회한다")
    @GetMapping(value = "constellations")
    public List<ConstellationParams> getConstellation() {
        return constellationService.getConstellation();
    }

    @ApiOperation(value = "모든 별자리 이름 조회", notes = "모든 별자리 이름을 조회한다")
    @GetMapping(value = "constellations/constName")
    public List<ConstellationParams2> getConstNames() {
        return constellationService.getConstNames();
    }

    @ApiOperation(value = "당일 날짜에 보이는 별자리 조회", notes = "당일 날짜에 보이는 별자리를 조회한다")
    @GetMapping(value = "constellation/todayConst")
    public List<ConstellationParams> getTodayConst() {
        return constellationService.getTodayConst();
    }

    @ApiOperation(value = "별자리 상세 정보 조회", notes = "별자리 이름으로 별자리 상세 정보를 조회한다.")
    @GetMapping(value = "constellation/{constName}")
    public Constellation getDetailConst(@PathVariable("constName") String constName) {
        return constellationService.getDetailConst(constName);
    }

}
