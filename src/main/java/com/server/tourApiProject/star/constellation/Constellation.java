package com.server.tourApiProject.star.constellation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;


@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "constellation")
public class Constellation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long constId;

    @Column(nullable = false)
    private String constName;  //별자리 이름

    @Column(nullable = false)
    private String constImage;  //별자리 이미지

    @Column(nullable = false)
    private String constStory;    // 별자리 설화

    @Column(nullable = false)
    private String springConstMtd; // 봄 별자리 관측법

    @Column(nullable = false)
    private String summerConstMtd; // 여름 별자리 관측법

    @Column(nullable = false)
    private String fallConstMtd; // 가을 별자리 관측법

    @Column(nullable = false)
    private String winterConstMtd; // 갸울 별자리 관측법

    @Column(nullable = false)
    private String constBestMonth; // 가장 보기 좋은 달

    private String constPersonality; // 별자리
    private String constPeriod; // 별자리 기간(성격에 포함됨)

    private String constFeature; // 별자리 특징(배너 형식)

    @Column(nullable = false)
    @DateTimeFormat(pattern = "MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd", timezone = "Asia/Seoul")
    private String startDate;    // 별자리가 보이기 시작하는 날짜

    @Column(nullable = false)
    @DateTimeFormat(pattern = "MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd", timezone = "Asia/Seoul")
    private String endDate;    // 별자리가 보이기 끝나는 날짜

//    @DateTimeFormat(pattern = "MM-dd")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd", timezone = "Asia/Seoul")
//    private String startYear;    // 01-01
//
//    @DateTimeFormat(pattern = "MM-dd")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd", timezone = "Asia/Seoul")
//    private String endYear;    // 12-31
}