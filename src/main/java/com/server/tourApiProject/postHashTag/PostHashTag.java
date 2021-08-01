package com.server.tourApiProject.postHashTag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.tourApiProject.post.Post;
import com.server.tourApiProject.user.User;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "postHashTag")
public class PostHashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postHashTagListId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", insertable = false, updatable=false)
    private Post post;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long hashTagId;

    @Column(nullable = false)
    private String hashTagName;
}
