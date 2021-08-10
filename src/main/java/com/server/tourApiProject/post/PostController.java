package com.server.tourApiProject.post;

import com.server.tourApiProject.postHashTag.PostHashTag;
import com.server.tourApiProject.postHashTag.PostHashTagService;
import com.server.tourApiProject.postObservePoint.PostObservePoint;
import com.server.tourApiProject.postObservePoint.PostObservePointService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = {"2.1 게시물"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostHashTagService postHashTagService;
    private final PostObservePointService postObservePointService;

    @ApiOperation(value = "게시물 정보 조회", notes = "게시물 아이디로 게시물을 조회한다")
    @GetMapping(value = "post/{postId}")
    public Post getPost(@PathVariable("postId") Long postId) {
        return postService.getPost(postId);
    }

    @ApiOperation(value = "게시물정보 입력", notes = "게시물 정보를 입력한다")
    @PostMapping(value = "post/{observePointName}")
    public void createPost(@PathVariable("observePointName") String observePointName,@RequestBody PostParams postParams) {
        postService.createPost(observePointName,postParams);
    }

    @ApiOperation(value = "게시물 해시태그 조회", notes = "게시물 id로 해당 게시물의 게시물 해시태그를 조회한다")
    @GetMapping(value = "post/{postId}/postHashTag")
    public List<PostHashTag> getPostHashTag(@PathVariable("postId") Long postId) {
        return postHashTagService.getPostHashTag(postId);
    }
    @ApiOperation(value = "게시물 관측지 조회", notes = "게시물 id로 해당 게시물의 게시물 관측지를 조회한다")
    @GetMapping(value = "post/{postId}/postObservePoint")
    public PostObservePoint getPostObservePoint(@PathVariable("postId") Long postId) {
        return postObservePointService.getPostObservePoint(postId);
    }


    @ApiOperation(value = "내 게시물 정보 조회", notes = "해당 사용자의 게시물을 조회한다")
    @GetMapping(value = "post/user/{userId}")
    public List<PostParams2> getMyPosts(@PathVariable("userId")Long userId ){ return postService.getMyPosts(userId); }

}

