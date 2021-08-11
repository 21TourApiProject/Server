package com.server.tourApiProject.bigPost.postObservePoint;

import lombok.*;

import javax.persistence.*;

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
    private Long observePointId;

    @Column(nullable = false)
    private String observePointName;
}
