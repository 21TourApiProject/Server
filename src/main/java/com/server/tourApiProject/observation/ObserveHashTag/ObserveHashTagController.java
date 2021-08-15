package com.server.tourApiProject.observation.ObserveHashTag;

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
public class ObserveHashTagController {
    private final ObserveHashTagService observeHashTagService;

    @ApiOperation(value = "선호 해시태그 리스트 입력", notes = "선호 해시태그 정보를 입력한다")
    @PostMapping(value = "myHashTag/{email}")
    public Long createMyHashTags(@PathVariable("email") String email, @RequestBody List<ObserveHashTagParams> observeHashTagParams){
        return observeHashTagService.createMyHashTags(email, observeHashTagParams);
    }
}
