package com.server.tourApiProject.notice;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = {"10.1 공지사항"})
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor

public class NoticeController {
    private final NoticeService noticeService;

    @ApiOperation(value = "공지사항 입력", notes = "공지사항을 입력한다")
    @PostMapping(value = "notice/")
    public void createNotice(@RequestBody NoticeParams noticeParams){
        noticeService.createNotice(noticeParams);
    }

    @ApiOperation(value = "모든 공지사항 조회", notes = "모든 공지사항를 조회한다")
    @GetMapping(value = "notice/all")
    public List<Notice> getAllNotice(){ return noticeService.getAllNotice(); }

    @ApiOperation(value = "공지사항 조회", notes = "공지사항의 정보를 조회한다")
    @GetMapping(value = "notice/{noticeId}")
    public Notice getNotice(@PathVariable("noticeId") Long noticeId){ return noticeService.getNotice(noticeId); }
}
