package com.server.tourApiProject.observation.course;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = {"6.1 관측지"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor

public class CourseController {
}
