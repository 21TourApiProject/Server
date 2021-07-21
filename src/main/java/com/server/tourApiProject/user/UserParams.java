package com.server.tourApiProject.user;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserParams {

    private Long userId;

    private String email;

    private String nickName;

    private String mobilePhoneNumber;
}
