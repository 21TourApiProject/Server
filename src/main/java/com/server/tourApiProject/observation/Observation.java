package com.server.tourApiProject.observation;

import com.server.tourApiProject.bigPost.postHashTag.PostHashTag;
import com.server.tourApiProject.bigPost.postImage.PostImage;
import com.server.tourApiProject.observation.ObserveHashTag.ObserveHashTag;
import com.server.tourApiProject.observation.ObserveImage.ObserveImage;
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
    private String Link;

    @Column(nullable = false)
    private float pointCrdX;    //지도를 위한 x좌표

    @Column(nullable = false)
    private float pointCrdY;    //지도를 위한 y좌표

    @Column(nullable = false)
    private String address;

    @Column
    private String phoneNumber;

    @Column
    private String operatingHour;

    @Column
    private String entranceFee;

    @Column
    private String parking; //주차안내

    @Column
    private String parkingImg;  //주차안내 사진진

    @Column
    private String intro;   //한줄소개

    @Column
    private String type;    //관측지 타입(천문대,등등), 추후 enum으로 수정가능?

    @Column
    private String outline; //개요

    @OneToMany(mappedBy = "observation")
    private List<ObserveHashTag> observeHashTags=new ArrayList<>();

    @OneToMany(mappedBy = "observation")
    private List<ObserveImage> observeImages = new ArrayList<>();

    //코스 추가 필요함함

}