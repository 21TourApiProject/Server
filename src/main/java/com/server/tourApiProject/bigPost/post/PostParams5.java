package com.server.tourApiProject.bigPost.post;

import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostParams5 {
        private Long postId;
        private String thumbnail;
        private String title;
        private String nickName;
        private String profileImage;
        private List<String> hashTagNames;
}
