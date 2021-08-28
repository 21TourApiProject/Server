package com.server.tourApiProject.myWish;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.tourApiProject.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

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
    private Long itemId; //관측지id 또는 관광지id 또는 게시물 id

    @Column(nullable = false)
    private LocalTime wishTime; //찜한 시간

}
