package com.server.tourApiProject.bigPost.post;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate yearDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private LocalTime time;

    private String postTitle;

    private String optionHashTag;
    private String optionHashTag2;
    private String optionHashTag3;
    private String optionHashTag4;
    private String optionHashTag5;
    private String optionHashTag6;
    private String optionHashTag7;
    private String optionHashTag8;
    private String optionHashTag9;
    private String optionHashTag10;

    private String optionObservation;

    private Long userId;
}
