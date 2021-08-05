package com.server.tourApiProject.myWishPost;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = {"1.3 내 찜 목록"})
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
}
