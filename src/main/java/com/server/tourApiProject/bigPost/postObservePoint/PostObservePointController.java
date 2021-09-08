package com.server.tourApiProject.bigPost.postObservePoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "게시물 관광지 정보 삭제", notes = "게시물 관광지 정보를 삭제한다")
    @DeleteMapping(value = "postObservePoint/{postObservePointId}")
    public void deletePostObservePoint(@PathVariable Long postObservePointId){
        postObservePointService.deletePostObservePoint(postObservePointId);
    }
}
