package com.server.tourApiProject.post;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostParams {

    private Long postId;

    private String content;

    private Long userId;
}
