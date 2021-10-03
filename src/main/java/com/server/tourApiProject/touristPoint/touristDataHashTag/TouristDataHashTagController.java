package com.server.tourApiProject.touristPoint.touristDataHashTag;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(tags = {"5.5 관광지-해시태그"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class TouristDataHashTagController {

    private final TouristDataHashTagService touristDataHashTagService;

    @ApiOperation(value = "관광지 해시태그 조희", notes = "관광지 해시태그 정보를 조희한다")
    @GetMapping(value = "touristDataHashTag/{contentId}")
    public List<String> getTouristDataHashTag(@PathVariable("contentId") Long contentId){
        return touristDataHashTagService.getTouristDataHashTag(contentId);
    }
}
