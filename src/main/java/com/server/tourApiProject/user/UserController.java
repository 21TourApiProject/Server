package com.server.tourApiProject.user;

import com.server.tourApiProject.myHashTag.MyHashTag;
import com.server.tourApiProject.myHashTag.MyHashTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = {"1.1 사용자"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final MyHashTagService myHashTagService;

    @ApiOperation(value = "사용자 조회", notes = "사용자 id로 사용자를 조회한다")
    @GetMapping(value = "user/{userId}")
    public User getUser(@PathVariable("userId") Long userId){ return userService.getUser(userId); }

    @ApiOperation(value = "사용자정보 입력", notes = "사용자 정보를 입력한다")
    @PostMapping(value = "user")
    public void createUser(@RequestBody UserParams userParam){
        userService.createUser(userParam);
    }

    @ApiOperation(value = "사용자정보 수정", notes = "사용자 정보를 수정한다")
    @PutMapping(value = "user/{userId}")
    public void updateUser(@PathVariable("userId") Long userId, @RequestBody UserParams userParam){
        userService.updateUser(userId, userParam);
    }

    @ApiOperation(value = "중복 이메일 조회", notes = "중복된 이메일이 있는지 조회한다")
    @GetMapping(value = "user/duplicate/email/{email}")
    public Boolean checkDuplicateLoginId(@PathVariable("email") String email){ return userService.checkDuplicateEmail(email); }

    @ApiOperation(value = "중복 전화번호 조회", notes = "중복된 전화번호가 있는지 조회한다")
    @GetMapping(value = "user/duplicate/mobilePhoneNumber/{mobilePhoneNumber}")
    public Boolean checkDuplicateMobilePhoneNumber(@PathVariable("mobilePhoneNumber") String mobilePhoneNumber){ return userService.checkDuplicateMobilePhoneNumber(mobilePhoneNumber); }

    @ApiOperation(value = "선호 해시태그 조회", notes = "사용자 id로 해당 사용자의 선호 해시태그를 조회한다")
    @GetMapping(value = "user/{userId}/myHashTags")
    public List<MyHashTag> getMyHashTags(@PathVariable("userId") Long userId){ return myHashTagService.getMyHashTags(userId); }

}
