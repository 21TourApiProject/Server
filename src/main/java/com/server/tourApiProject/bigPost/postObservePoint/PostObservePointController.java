package com.server.tourApiProject.bigPost.postObservePoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = {"2.4 게시물 관측지"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class PostObservePointController {
    private final PostObservePointService postObservePointService;
    @ApiOperation(value = "게시물 관측지 입력", notes = "게시물 관측지 정보를 입력한다")
    @PostMapping(value = "postObservePoint")
    public void createPostObeservePoint(@RequestBody PostObservePointParams postObservePointParams){
        postObservePointService.createPostObservePoint(postObservePointParams);
    }
}
