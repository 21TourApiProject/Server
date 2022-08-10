package com.server.tourApiProject.bigPost.post;

import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
* @className : PostParams5.java
* @description : 찜 목록의 게시물 Param 입니다.
* @modification : 2022-08-08(jinhyeok) 주석 수정
* @author : jinhyeok
* @date : 2022-08-08
* @version : 1.0
   ====개정이력(Modification Information)====
  수정일        수정자        수정내용
   -----------------------------------------
   2022-08-08       jinhyeok       주석 수정

 */
public class PostParams5 {
        private Long postId;
        private String thumbnail;
        private String title;
        private String nickName;
        private String profileImage;
        private List<String> hashTagNames;
}
