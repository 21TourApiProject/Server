package com.server.tourApiProject.myHashTag;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = {"1.3 선호 해시태그"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class MyHashTagController {
    private final MyHashTagService myHashTagService;

    @ApiOperation(value = "선호 해시태그 입력", notes = "선호 해시태그 정보를 입력한다")
    @PostMapping(value = "myHashTag")
    public void createMyHashTag(@RequestBody MyHashTagParams myHashTagParam){
        myHashTagService.createMyHashTag(myHashTagParam);
    }
}
