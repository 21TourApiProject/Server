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

//    @Column(nullable = false)
//    private String constBigImage;  //별자리 큰 이미지
//
//    @Column(nullable = false)
//    private String constSmallImage;  //별자리 작은 이미지

    @Column(nullable = false, columnDefinition = "TEXT")
    private String constStory;    // 별자리 설화

    @Column(nullable = false, columnDefinition = "TEXT")
    private String constMtd;    // 별자리 관측법

    @Column(columnDefinition = "TEXT")
    private String constTravel; // 여행 취향

    @Column(nullable = false)
    private String constBestMonth; // 가장 보기 좋은 달

    @Column(columnDefinition = "TEXT")
    private String constPersonality; // 별자리 성격
    private String constPeriod; // 별자리 기간(성격에 포함됨)

    private String constFeature1; // 별자리 특징(배너 형식)

    private String constFeature2; // 별자리 특징(배너 형식)

    private String constFeature3; // 별자리 특징(배너 형식)

    private String constGuard; // 수호성

    @Column(nullable = false)
    @DateTimeFormat(pattern = "MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd", timezone = "Asia/Seoul")
    private String startDate1;    // 별자리가 보이기 시작하는 날짜

    @Column(nullable = false)
    @DateTimeFormat(pattern = "MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd", timezone = "Asia/Seoul")
    private String endDate1;    // 별자리가 보이기 끝나는 날짜

    @DateTimeFormat(pattern = "MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd", timezone = "Asia/Seoul")
    private String startDate2;    // 별자리가 보이기 시작하는 날짜

    @DateTimeFormat(pattern = "MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd", timezone = "Asia/Seoul")
    private String endDate2;    // 별자리가 보이기 끝나는 날짜

    @Column(nullable = false)
    private String constEng;  //별자리 이름


//    @DateTimeFormat(pattern = "MM-dd")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd", timezone = "Asia/Seoul")
//    private String startYear;    // 01-01
//
//    @DateTimeFormat(pattern = "MM-dd")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd", timezone = "Asia/Seoul")
//    private String endYear;    // 12-31
}
