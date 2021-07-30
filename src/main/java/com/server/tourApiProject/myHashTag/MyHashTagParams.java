package com.server.tourApiProject.myHashTag;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyHashTagParams {
    private Long userId;

    private int hashTagId;
}
