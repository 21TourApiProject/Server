package com.server.tourApiProject.myWish;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = {"1.3 내 찜"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class MyWishController {
    private final MyWishService myWishService;

    @ApiOperation(value = "내 찜 입력", notes = "찜한 것(관측지 또는 관광지 또는 게시물)의 정보를 입력한다")
    @PostMapping(value = "myWish/{userId}/{itemId}/{wishType}")
    public void createMyWish(@PathVariable("userId") Long userId, @PathVariable("itemId") Long itemId, @PathVariable("wishType") Integer wishType){
        myWishService.createMyWish(userId, itemId, wishType);
    }

    @ApiOperation(value = "내 찜 관측지 조회", notes = "해당 사용자가 찜한 모든 '관측지' 목록을 조회한다")
    @GetMapping(value = "myWish/observation/{userId}")
    public List<MyWishParams01> getMyWishObservation(@PathVariable("userId") Long userId){
        return myWishService.getMyWishObservation(userId);
    }

    @ApiOperation(value = "내 찜 관광지 조회", notes = "해당 사용자가 찜한 모든 '관광지' 목록을 조회한다")
    @GetMapping(value = "myWish/touristPoint/{userId}")
    public List<MyWishParams01> getMyWishTouristPoint(@PathVariable("userId") Long userId){
        return myWishService.getMyWishTouristPoint(userId);
    }

    @ApiOperation(value = "내 찜 게시물 조회", notes = "해당 사용자가 찜한 모든 '게시물' 목록을 조회한다")
    @GetMapping(value = "myWish/post/{userId}")
    public List<MyWishParams2> getMyWishPost(@PathVariable("userId") Long userId){
        return myWishService.getMyWishPost(userId);
    }

    @ApiOperation(value = "내 찜 3개 조회", notes = "해당 사용자가 최근에 찜한 것(최대 3개)을 조회한다")
    @GetMapping(value = "myWish/{userId}")
    public List<MyWishParams3> getMyWish(@PathVariable("userId") Long userId){
        return myWishService.getMyWish(userId);
    }
}
