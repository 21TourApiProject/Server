package com.server.tourApiProject.post;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostParams {

    private String postContent;

    private LocalDate yearDate;

    private LocalTime time;

    private Long userId;

    private Long postObservePointId;
}
