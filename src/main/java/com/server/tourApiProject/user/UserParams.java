package com.server.tourApiProject.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserParams {

    @ApiModelProperty(value = "사용자 ID", required = true)
    private Long userId;

    @ApiModelProperty(value = "이메일", required = true)
    private String email;

    @ApiModelProperty(value = "닉네임", required = true)
    private String nickName;

    @ApiModelProperty(value = "휴대폰 번호", required = true)
    private String mobilePhoneNumber;
}
