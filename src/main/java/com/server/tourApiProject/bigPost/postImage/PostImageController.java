package com.server.tourApiProject.bigPost.postImage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = {"2.3 게시물 이미지"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class PostImageController {
    private final PostImageService postImageService;

    @ApiOperation(value = "게시물 이미지 추가", notes = "게시물 이미지를 추가한다")
    @PostMapping(value = "postImage/{postId}")
    public void createPostImage(@PathVariable("postId") Long postId, @RequestBody List<PostImageParams> postImageParams){
        postImageService.createPostImage(postId,postImageParams);
    }
    @ApiOperation(value = "게시물 이미지 이름", notes = "게시물 이미지이름을 id로 조회한다")
    @GetMapping(value = "postImage/{postImageListId}")
    public String getPostImageName(@PathVariable("postContent") Long postImageListId){
        return postImageService.getPostImageName(postImageListId);
    }
}
