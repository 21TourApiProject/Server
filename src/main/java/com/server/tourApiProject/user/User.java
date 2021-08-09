package com.server.tourApiProject.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.tourApiProject.myHashTag.MyHashTag;
import com.server.tourApiProject.myWishPost.MyWishPost;
import com.server.tourApiProject.post.Post;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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

    @Column
    private String realName;

    @Column
    private Boolean sex; //남자 0, 여자 1

    @Column
    private String birthDay;

    @Column(unique = true, length = 16)
    private String mobilePhoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    @Column(nullable = false, unique = true, length = 20)
    private String nickName;

    @Column
    private String profileImage;

    @Column
    private String ageRange; //연령대

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Post> myPosts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MyHashTag> myHashTags = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MyWishPost> myWishPosts = new ArrayList<>();

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime signUpDt;

}
