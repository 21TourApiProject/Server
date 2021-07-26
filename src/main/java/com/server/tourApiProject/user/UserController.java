package com.server.tourApiProject.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = {"1.1 사용자"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "사용자 조회", notes = "사용자 id로 사용자를 조회한다")
    @GetMapping(value = "user/{userId}")
    public User getUser(@PathVariable("userId") Long userId){ return userService.getUser(userId); }

    @ApiOperation(value = "사용자정보 입력", notes = "사용자 정보를 입력한다")
    @PostMapping(value = "user/")
    public void createUser(@RequestBody UserParams userParam){
        userService.createUser(userParam);
    }

    @ApiOperation(value = "사용자정보 수정", notes = "사용자 정보를 수정한다")
    @PutMapping(value = "user/{userId}")
    public void updateUser(@PathVariable("userId") Long userId, @RequestBody UserParams userParam){
        userService.updateUser(userId, userParam);
    }

    @ApiOperation(value = "중복 아이디 조회", notes = "중복된 아이디가 있는지 조회한다")
    @GetMapping(value = "user/duplicate/{loginId}")
    public Boolean checkDuplicateLoginId(@PathVariable("loginId") String loginId){ return userService.checkDuplicateLoginId(loginId); }

}
