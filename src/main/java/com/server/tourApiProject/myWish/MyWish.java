package com.server.tourApiProject.myWish;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.tourApiProject.user.User;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="myWish")
public class MyWish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long myWishId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", insertable = false, updatable=false)
    private User user;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Integer wishType; //0이면 관측지, 1이면 관광지, 2면 게시물

    @Column
    private Long observationId; //관측지 id

    @Column
    private Long contentId; //관광지 id

    @Column
    private Long postId; //게시물 id
}
