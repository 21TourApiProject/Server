package com.server.tourApiProject.myWishPost;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyWishPostParams {

    private String thumbnail;
    private String title;
    private Long postId;
}
