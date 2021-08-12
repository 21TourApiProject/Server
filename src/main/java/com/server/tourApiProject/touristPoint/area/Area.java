package com.server.tourApiProject.touristPoint.area;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long areaCode; //지역코드

    @Column(nullable = false)
    private String areaName; //지역이름

    @Column
    private Long sigunguCode; //시군구코드

    @Column
    private String sigunguName; //시군구이름
}
