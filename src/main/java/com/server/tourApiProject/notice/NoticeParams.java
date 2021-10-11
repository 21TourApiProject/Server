package com.server.tourApiProject.notice;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeParams {
    private String noticeTitle;
    private String noticeContent;
    private String noticeDate;
}
