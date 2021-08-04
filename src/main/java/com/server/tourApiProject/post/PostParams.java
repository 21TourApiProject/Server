package com.server.tourApiProject.post;

import java.time.LocalDateTime;
import java.util.List;

import com.server.tourApiProject.hashTag.HashTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostParams {

    private String postContent;

    private String postImage;

    private String observeFit;

    private LocalDateTime yearDate;

    private LocalDateTime time;

    private Long userId;
}
