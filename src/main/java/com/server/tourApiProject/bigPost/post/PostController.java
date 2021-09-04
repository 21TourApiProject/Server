package com.server.tourApiProject.bigPost.post;

import com.server.tourApiProject.bigPost.postHashTag.PostHashTag;
import com.server.tourApiProject.bigPost.postHashTag.PostHashTagService;
import com.server.tourApiProject.bigPost.postImage.PostImageService;
import com.server.tourApiProject.bigPost.postObservePoint.PostObservePoint;
import com.server.tourApiProject.bigPost.postObservePoint.PostObservePointService;
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
    private final PostImageService postImageService;

    @ApiOperation(value = "게시물 정보 조회", notes = "게시물 아이디로 게시물을 조회한다")
    @GetMapping(value = "post/{postId}")
    public Post getPost(@PathVariable("postId") Long postId) {
        return postService.getPost(postId);
    }

    @ApiOperation(value = "게시물정보 입력", notes = "게시물 정보를 입력한다")
    @PostMapping(value = "post/{observePointName}")
    public Long createPost(@PathVariable("observePointName") String observePointName,@RequestBody PostParams postParams) {
        return postService.createPost(observePointName,postParams);
    }
    @ApiOperation(value = "게시물정보 삭제", notes = "게시물 정보를 삭제한다")
    @DeleteMapping(value = "post/{userId}")
    public void deletePost(@PathVariable("userId") Long userId){
//        postService.deletePost(userId);
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
    @ApiOperation(value = "게시물 이미지 조회", notes = "게시물 id로 해당 게시물의 게시물 이미지를 조회한다")
    @GetMapping(value = "post/{postId}/postImage")
    public List<String> getPostImage(@PathVariable("postId") Long postId) {
        return postImageService.getPostImage(postId);
    }
    @ApiOperation(value = "관련 게시물 이미지 검색", notes = "게시물 관측지 id로 이미지를 조회한다")
    @GetMapping(value = "postImage/{postObservePointId}")
    public List<String> getRelatePostImageList(@PathVariable("postObservePointId") Long postObservePointId){
        return postImageService.getRelatePostImageList(postObservePointId);
    }

    @ApiOperation(value = "내 게시물 정보 조회", notes = "해당 사용자의 게시물을 조회한다")
    @GetMapping(value = "post/user/{userId}")
    public List<PostParams3> getMyPost(@PathVariable("userId")Long userId ){ return postService.getMyPost(userId); }

}

