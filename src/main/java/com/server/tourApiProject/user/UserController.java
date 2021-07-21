package com.server.tourApiProject.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = {"1.1 사용자"})
@RestController(value = "/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "사용자 조회", notes = "사용자 id로 사용자를 조회한다")
    @GetMapping(value = "user/{userId}")
    public User getUser(@PathVariable("userId") Long userId){
        return userService.getUser(userId);
    }

    @ApiOperation(value = "사용자정보 입력", notes = "사용자 정보를 입력한다")
    @PostMapping(value = "user/")
    public void createUser(@RequestBody UserParam userParam){
        userService.createUser(userParam);
    }

}
