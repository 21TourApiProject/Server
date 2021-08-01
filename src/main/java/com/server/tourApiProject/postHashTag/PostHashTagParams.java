package com.server.tourApiProject.postHashTag;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostHashTagParams {

    private String mobilePhoneNumber;

    private String hashTagName;
}
