package com.server.tourApiProject.notice;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;

    @Column(nullable = false)
    private String noticeTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String noticeContent;

    @Column(nullable = false)
    private String noticeDate;
}
