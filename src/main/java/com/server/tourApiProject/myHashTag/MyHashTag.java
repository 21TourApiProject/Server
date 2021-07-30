package com.server.tourApiProject.myHashTag;

import com.server.tourApiProject.user.User;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="myHashTag")
public class MyHashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long myHashTagListId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", insertable = false, updatable=false)
    private User user;

    private Long userId;

    @Column(nullable = false)
    private int hashTagId;
}
