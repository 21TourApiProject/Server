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

}
