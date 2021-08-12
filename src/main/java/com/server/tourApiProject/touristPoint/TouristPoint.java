package com.server.tourApiProject.touristPoint;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="observation")
public class TouristPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long observationId;

    @Column(nullable = false)
    private String address; //주소(공통)

    @Column(nullable = false)
    private String phone; //문의(공통)

    @Column
    private String operatingHour; //운영시간

    @Column(nullable = false)
    private String park; //주차(공통?)

    @Column
    private String animal; //반려동물

    @Column
    private String homePage; //홈페이지

    @Column
    private String congestion; //혼잡도

    @Column


    private String operatingHour2; //영업시간

    @Column
    private String mainMenu; //대표메뉴

    @Column
    private String reservation; //예약안내

    @Column
    private String takeOut; //포장




}
