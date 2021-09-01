package com.server.tourApiProject.bigPost.postObservePoint;

import com.server.tourApiProject.bigPost.post.Post;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "postObservePoint")
public class PostObservePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postObservePointId;

    @Column(nullable = false)
    private String observePointName;

    @OneToMany(mappedBy = "postObservePoint", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Post> posts=new ArrayList<>();
}
