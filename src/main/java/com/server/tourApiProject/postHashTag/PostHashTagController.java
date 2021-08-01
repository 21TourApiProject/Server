package com.server.tourApiProject.postHashTag;

import com.server.tourApiProject.myHashTag.MyHashTagParams;
import com.server.tourApiProject.myHashTag.MyHashTagService;
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
@Api(tags = {"2.2 게시물 해시태그"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class PostHashTagController {
    private final PostHashTagService postHashTagService;

    @ApiOperation(value = "게시물 해시태그 리스트 입력", notes = "게시물 해시태그 정보를 입력한다")
    @PostMapping(value = "postHashTag")
    public void createPostHashTags(@RequestBody List<PostHashTagParams> postHashTagParams){
        postHashTagService.createPostHashTags(postHashTagParams);
    }
}
