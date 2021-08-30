package com.server.tourApiProject.touristPoint.touristData;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TouristDataCourseParams {

    private Long contentTypeId; //12
    private String firstImage; //대표이미지 원본
    private String title; //제목
    private String cat3Name; //소분류 이름
    private String overview; //개요
    private String addr1; //주소
    private String useTime; //이용시간(관광지)
    private String parking; //주차시설(관광지)
    private String treatMenu; //취급메뉴(음식)
}
