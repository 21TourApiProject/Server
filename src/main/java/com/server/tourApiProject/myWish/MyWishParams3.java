package com.server.tourApiProject.myWish;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyWishParams3 {
    private String thumbnail; //썸네일
    private String title; //제목
    private Integer wishType; //0이면 관측지, 1이면 관광지, 2면 게시물
}
