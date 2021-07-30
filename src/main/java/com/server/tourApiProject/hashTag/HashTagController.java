package com.server.tourApiProject.hashTag;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = {"1.2 해시태그"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class HashTagController {

    private final HashTagService hashTagService;

    @ApiOperation(value = "해시태그 조회", notes = "모든 해시태그를 조회한다")
    @GetMapping(value = "hashTag")
    public List<HashTag> getHashTag(){ return hashTagService.getAllHashTag(); }

    @ApiOperation(value = "해시태그 입력", notes = "해시태그 정보를 입력한다")
    @PostMapping(value = "hashTag")
    public void createHashTag(@RequestBody HashTag hashTag){
        hashTagService.createHashTag(hashTag);
    }
}
