package com.server.tourApiProject.touristPoint.contentType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = {"3.2 관광지-서비스 분류"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class ContentTypeController {

    private final ContentTypeService contentTypeService;

    @ApiOperation(value = "서비스 정보 입력(관광지)", notes = "관광지 서비스 정보를 입력한다")
    @PostMapping(value = "contentType/touristSpot")
    public void createContentType1(@RequestBody ContentTypeParams2 contentTypeParams2){
        contentTypeService.createContentType1(contentTypeParams2);
    }

    @ApiOperation(value = "서비스 정보 입력(음식)", notes = "음식 서비스 정보를 입력한다")
    @PostMapping(value = "contentType/food")
    public void createContentType2(@RequestBody ContentTypeParams2 contentTypeParams2){
        contentTypeService.createContentType2(contentTypeParams2);
    }
}
