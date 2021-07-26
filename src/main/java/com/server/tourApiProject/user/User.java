package com.server.tourApiProject.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.tourApiProject.post.Post;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String realName;

    @Column(nullable = false)
    private Boolean sex; //남자 0, 여자 1

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDay;

    @Column(nullable = false, unique = true, length = 16)
    private String mobilePhoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickName;

    private String profileImage;

    //private List<Review> myReviews = new ArrayList<>();

    //private List<Post> myPosts = new ArrayList<>();

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime signUpDt;

//    @ElementCollection
//    @Column(nullable = false)
//    private List<String> userHashTags = new ArrayList<String>();

}
