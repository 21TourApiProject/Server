package com.server.tourApiProject.post;

import com.server.tourApiProject.myHashTag.MyHashTag;
import com.server.tourApiProject.postHashTag.PostHashTag;
import com.server.tourApiProject.postHashTag.PostHashTagService;
import com.server.tourApiProject.user.User;
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

    @ApiOperation(value = "게시물 정보 조회", notes = "게시물 아이디로 게시물을 조회한다")
    @GetMapping(value = "post/{postId}")
    public Post getPost(@PathVariable("postId")Long postId ){return postService.getPost(postId);}

    @ApiOperation(value = "게시물정보 입력", notes = "게시물 정보를 입력한다")
    @PostMapping(value = "post")
    public void createPost(@RequestBody PostParams postParams){
        postService.createPost(postParams);
    }

    @ApiOperation(value = "게시물 해시태그 조회", notes = "게시물 id로 해당 게시물의 게시물 해시태그를 조회한다")
    @GetMapping(value = "post/{postId}/postHashTag")
    public List<PostHashTag> getPostHashTag(@PathVariable("postId") Long postId){ return postHashTagService.getPostHashTag(postId); }
    }

