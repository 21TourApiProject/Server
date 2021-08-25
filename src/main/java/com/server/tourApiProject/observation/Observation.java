package com.server.tourApiProject.observation;

import com.server.tourApiProject.observation.observeHashTag.ObserveHashTag;
import com.server.tourApiProject.observation.observeImage.ObserveImage;
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
@Table(name="observation")
public class Observation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long observationId;

    @Column(nullable = false, unique = true)
    private String observationName;

    @Column
    private String link;

    @Column(nullable = false)
    private double latitude;    //지도를 위한 위도

    @Column(nullable = false)
    private double longitude;    //지도를 위한 경도

    @Column(nullable = false)
    private String address;

    @Column
    private String phoneNumber; //문의

    @Column
    private String operatingHour;

    @Column
    private String entranceFee;

    @Column
    private String parking; //주차안내

    @Column
    private String observeType;    //관측지 타입(천문대,등등), 추후 enum으로 수정가능?

    @Column
    private String outline; //개요

    @Column
    private String guide;   //이용안내

    @Column
    private String closedDay;   //휴무일

    @Column
    private double light;   //광공해

    @Column
    private boolean nature;   //자연관광지

    @OneToMany(mappedBy = "observation")
    private List<ObserveHashTag> observeHashTags=new ArrayList<>();

    @OneToMany(mappedBy = "observation")
    private List<ObserveImage> observeImages = new ArrayList<>();

    //코스 추가 필요함함

}