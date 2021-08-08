package com.server.tourApiProject.user;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoUserParams {

    private String nickName;

    private Boolean sex;

    private String birthDay;

    private String mobilePhoneNumber;

    private String email;

    private String profileImage;

    private String ageRange;

}
