package com.server.tourApiProject.post;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostParams2 {
    private String thumbnail;
    private String title;
    private Long postId;
}
