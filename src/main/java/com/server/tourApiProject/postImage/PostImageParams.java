package com.server.tourApiProject.postImage;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostImageParams {
    private Long postId;

    private String imageName;
}