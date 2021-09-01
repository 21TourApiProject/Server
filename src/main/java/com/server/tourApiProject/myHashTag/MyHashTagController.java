package com.server.tourApiProject.myHashTag;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = {"1.2 선호 해시태그"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class MyHashTagController {
    private final MyHashTagService myHashTagService;

    @ApiOperation(value = "선호 해시태그 리스트 입력", notes = "선호 해시태그 정보를 입력한다")
    @PostMapping(value = "myHashTag/{email}")
    public Long createMyHashTags(@PathVariable("email") String email, @RequestBody List<MyHashTagParams> myHashTagParams){
        return myHashTagService.createMyHashTags(email, myHashTagParams);
    }

    @ApiOperation(value = "선호 해시태그 조회", notes = "사용자 id로 해당 사용자의 선호 해시태그를 조회한다")
    @GetMapping(value = "user/{userId}/myHashTag")
    public List<String> getMyHashTag(@PathVariable("userId") Long userId){ return myHashTagService.getMyHashTag(userId); }
}
