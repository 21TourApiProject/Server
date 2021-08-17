package com.server.tourApiProject.touristPoint.touristData;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="touristData")
public class TouristData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long touristDataId;

    @Column
    private String addr1; //주소

    @Column
    private String addr2; //상세주소

    @Column
    private Long areaCode; //지역 코드

    @Column
    private Long sigunguCode; //시군구 코드

    @Column
    private String cat1; //대분류 코드

    @Column
    private String cat2; //중분류 코드

    @Column
    private String cat3; //소분류 코드

    @Column(nullable = false, unique = true)
    private Long contentId; //콘텐츠 ID

    @Column(nullable = false)
    private Long contentTypeId; //콘텐츠타입 ID

    @Column
    private String firstImage; //대표이미지 원본

    @Column
    private String firstImage2; //대표이미지 화질낮은

    @Column
    private Double mapX; //GPS X좌표

    @Column
    private Double mapY; //GPS Y좌표

    @Column
    private Long readCount; //조회수

    @Column
    private String tel; //전화번호

    @Column
    private String title; //제목

    @Column
    private Long zipcode; //우편번호

    @Column(length = 50000)
    private String overview; //개요

    @Column(length = 50000)
    private String homePage; //홈페이지(관광지)

    @Column
    private String useTime; //이용시간(관광지)

    @Column
    private String restDate; //휴무일(관광지)

    @Column(length = 50000)
    private String expGuide ; //체험안내(관광지)

    @Column
    private String parking; //주차시설(관광지)

    @Column
    private String chkPet; //반려동물(관광지)

    @Column
    private String openTimeFood; //영업시간(음식)

    @Column
    private String restDateFood; //휴무일(음식)

    @Column
    private String firstMenu; //대표메뉴(음식)

    @Column
    private String treatMenu; //취급메뉴(음식)

    @Column
    private String packing; //포장(음식)

    @Column
    private String parkingFood; //주차시설(음식)

}
