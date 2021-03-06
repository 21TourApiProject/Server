package com.server.tourApiProject.bigPost.post;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostParams4 {
    private Long postId;
    private Long observationId;
    private String mainObservation;
    private String optionObservation;
    private String mainTitle;
    private String mainNickName;
    private ArrayList<String> images;
    private List<String> hashTags;
    private String optionHashTag;
    private String optionHashTag2;
    private String optionHashTag3;
    private String profileImage;
}
