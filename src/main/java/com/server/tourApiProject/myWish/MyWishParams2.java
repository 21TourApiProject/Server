package com.server.tourApiProject.myWish;

import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyWishParams2 {

    private Long itemId; //게시물 id
    private String thumbnail; //썸네일
    private String title; //제목
    private String nickName; //작성자
    private String profileImage; //프로필 사진
    private List<String> hashTagNames; //해시태그 배열
}
