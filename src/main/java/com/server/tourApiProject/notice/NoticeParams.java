package com.server.tourApiProject.notice;

import lombok.*;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeParams {
    private String noticeTitle;
    private String noticeContent;
    private LocalDate date;
}
