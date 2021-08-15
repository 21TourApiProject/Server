package com.server.tourApiProject.touristPoint.touristData;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TouristDataParams2 {

    private Long contentTypeId; //39
    private String firstImage; //대표이미지 원본
    private String title; //제목
    //private String overviewSimple; //개요 1줄
    private String cat3Name; //소분류 이름
    private String overview; //개요
    private String addr1; //주소
    private String tel; //전화번호
    private String openTimeFood; //영업시간(음식)
    private String restDateFood; //휴무일(음식)
    private String firstMenu; //대표메뉴(음식)
    private String treatMenu; //취급메뉴(음식)
    private String packing; //포장(음식)
    private String parkingFood; //주차시설(음식)
}
