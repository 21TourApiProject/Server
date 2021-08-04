package com.server.tourApiProject.postImage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.tourApiProject.post.Post;
import lombok.*;

import javax.persistence.*;
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "postImage")
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postImageListId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", insertable = false, updatable=false)
    private Post post;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long ImageId;

    @Column(nullable = false)
    private String ImageName;
}
