package com.server.tourApiProject.hashTag;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(tags = {"2.1 해시태그"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class HashTagController {

    private final HashTagService hashTagService;

    @ApiOperation(value = "해시태그 조회", notes = "모든 해시태그를 조회한다")
    @GetMapping(value = "hashTag")
    public List<HashTag> getHashTag(){ return hashTagService.getAllHashTag(); }
}
