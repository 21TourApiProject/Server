package com.server.tourApiProject.myWishPost;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = {"1.3 내 찜 - 게시물"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class MyWishPostController {
    private final MyWishPostService myWishPostService;

    @ApiOperation(value = "내 찜 목록 입력", notes = "찜한 게시물의 정보를 입력한다")
    @PostMapping(value = "myWishPost/{userId}/{postId}")
    public void createMyWishPost(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId){
        myWishPostService.createMyWishPost(userId, postId);
    }

    @ApiOperation(value = "내 찜 목록 조회", notes = "해당 사용자가 찜한 게시물 목록을 조회한다")
    @GetMapping(value = "myWishPost/{userId}")
    public List<MyWishPostParams> getMyWishPosts(@PathVariable("userId") Long userId){
        return myWishPostService.getMyWishPosts(userId);
    }
}
