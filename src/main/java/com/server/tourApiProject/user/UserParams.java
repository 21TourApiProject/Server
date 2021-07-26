package com.server.tourApiProject.user;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserParams {

    private String realName;

    private Boolean sex;

    private LocalDate birthDay;

    private String mobilePhoneNumber;

    private String email;

    private String loginId;

    private String password;

    //private List<String> userHashTags;


}
