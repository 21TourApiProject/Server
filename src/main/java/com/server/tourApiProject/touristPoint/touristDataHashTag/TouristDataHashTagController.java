package com.server.tourApiProject.touristPoint.touristDataHashTag;

import com.server.tourApiProject.myWish.MyWishParams01;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

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

    @ApiOperation(value = "해시태그 리스트로 관광지 조희", notes = "해당 해시태그가 붙은 관광지 정보를 조희한다")
    @GetMapping(value = "touristDataHashTag/search/{hashTagIdList}")
    public List<MyWishParams01> getTouristDataWithHashTag(@PathVariable("hashTagIdList") List<Long> hashTagIdList){
        return touristDataHashTagService.getTouristDataWithHashTag(hashTagIdList);
    }
}
