package com.server.tourApiProject.user;

import io.swagger.annotations.ApiModelProperty;
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
