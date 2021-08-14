package com.server.tourApiProject.bigPost.postHashTag;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = {"2.2 게시물 해시태그"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class PostHashTagController {
    private final PostHashTagService postHashTagService;

    @ApiOperation(value = "게시물 해시태그 리스트 입력", notes = "게시물 해시태그 정보를 입력한다")
    @PostMapping(value = "postHashTag/{postId}")
    public void createPostHashTags(@PathVariable("postId")  Long postId, @RequestBody List<PostHashTagParams> postHashTagParams){
        postHashTagService.createPostHashTags(postId,postHashTagParams);
    }
}
