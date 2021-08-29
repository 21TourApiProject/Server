package com.server.tourApiProject.myWish;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyWishParams01 {

    private Long itemId; //관측지id 또는 관광지 id
    private String thumbnail; //썸네일
    private String title; //제목
    private String address; //주소
    private String cat3; //분류 ex) 카페, 해변..
    private String overviewSim; //짧은 개요
    private List<Long> hashTags; //해시태그 배열
}