package com.server.tourApiProject.postHashTag;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostHashTagParams {
    private Long postId;

    private String hashTagName;
}
