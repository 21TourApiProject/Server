package com.server.tourApiProject.touristPoint.touristDataHashTag;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = {"5.5 관광지 해시태그"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class TouristDataHashTagController {

    private final TouristDataHashTagService touristDataHashTagService;

    @ApiOperation(value = "관광지 해시태그 입력", notes = "관광지 해시태그 정보를 입력한다")
    @PostMapping(value = "touristDataHashTag")
    public void createTouristDataHashTag(@RequestBody TouristDataHashTag touristDataHashTag){
        touristDataHashTagService.createTouristDataHashTag(touristDataHashTag);
    }
}
