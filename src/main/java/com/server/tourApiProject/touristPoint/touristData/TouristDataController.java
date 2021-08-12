package com.server.tourApiProject.touristPoint.touristData;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = {"5.3 관광지-관광 정보"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class TouristDataController {
}
