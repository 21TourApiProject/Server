package com.server.tourApiProject.postImage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(tags = {"2.3 게시물 이미지"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class PostImageController {
    private final PostImageService postImageService;

    @ApiOperation(value = "게시물 해시태그 이미지 추가", notes = "게시물 해시태그 이미지를 추가한다")
    @PostMapping(value = "postImage")
    public void createPostImage(@RequestBody List<PostImageParams> postImageParams){
        postImageService.createPostImage(postImageParams);
    }
}
