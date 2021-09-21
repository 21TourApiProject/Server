package com.server.tourApiProject.notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public List<Notice> getAllNotice() {
        return noticeRepository.findAll();
    }

    public Notice getNotice(Long noticeId) {
        return noticeRepository.findById(noticeId).orElseThrow(IllegalAccessError::new);
    }

    public void createNotice(NoticeParams noticeParams) {
        Notice notice = new Notice();
        notice.setNoticeTitle(noticeParams.getNoticeTitle());
        notice.setNoticeContent(noticeParams.getNoticeContent());
        notice.setNoticeDate(noticeParams.getNoticeDate());
        noticeRepository.save(notice);
    }
}
