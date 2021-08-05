package com.server.tourApiProject.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.tourApiProject.hashTag.HashTag;
import com.server.tourApiProject.postHashTag.PostHashTag;
import com.server.tourApiProject.postImage.PostImage;
import com.server.tourApiProject.user.User;

import org.springframework.format.annotation.DateTimeFormat;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(length = 1000,nullable = false)
    private String postContent;

    @Column(nullable = false)
    private String postImage;

    @Column(nullable = false)
    private String observeFit;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime yearDate;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "'T'HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime time;

    @OneToMany(mappedBy = "post")
    private List<PostHashTag> postHashTags=new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<PostImage> postImages=new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", insertable = false, updatable=false)
    private User user;

    private Long userId;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime registerDt;
}
