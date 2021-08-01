package com.server.tourApiProject.hashTag;

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
@Table(name="hashTag")
public class HashTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hashTagId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", insertable = false, updatable=false)
    private Post post;

    @Column(nullable = false, unique = true)
    private String hashTagName;
}
