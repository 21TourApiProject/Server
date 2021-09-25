package com.server.tourApiProject.search;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchParams1 {
    //검색결과에 쓸 관측지 params
    private Long itemId; //관측지id
    private String thumbnail; //썸네일
    private String title; //제목
    private String address; //주소
    private String contentType; //분류
    private String intro; //짧은 개요
    private Double longitude;  //경도
    private Double latitude; //위도
    private List<String> hashTagNames; //해시태그 배열
}
