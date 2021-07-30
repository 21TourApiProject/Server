package com.server.tourApiProject.myHashTag;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = {"1.3 선호 해시태그"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class MyHashTagController {
}
