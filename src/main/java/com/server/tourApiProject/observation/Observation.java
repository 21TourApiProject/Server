package com.server.tourApiProject.observation;

import com.server.tourApiProject.bigPost.postHashTag.PostHashTag;
import com.server.tourApiProject.bigPost.postImage.PostImage;
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
    private float PointCrdX;    //지도를 위한 x좌표

    @Column(nullable = false)
    private float PointCrdY;    //지도를 위한 y좌표

    @Column(nullable = false)
    private String Address;

    @Column
    private String PhoneNumber;

    @Column
    private String operatingHour;

    @Column
    private String entranceFee;

    @Column
    private String parking;

    @Column
    private String intro;   //한줄소개

    @Column
    private String type;    //관측지 타입(천문대,등등), 추후 enum으로 수정가능?

    @Column
    private String outline; //개요

//    @OneToMany(mappedBy = "observePoint")
//    private List<PostHashTag> postHashTags=new ArrayList<>();
//
//    @OneToMany(mappedBy = "post")
//    private List<PostImage> postImages=new ArrayList<>();

    //코스 추가 필요함함

}