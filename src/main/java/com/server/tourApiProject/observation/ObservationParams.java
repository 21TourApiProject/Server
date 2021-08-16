package com.server.tourApiProject.observation;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ObservationParams {

    private String observationName;

    private String link;

    private float latitude;

    private float longitude;

    private String address;

    private String phoneNumber;

    private String operatingHour;

    private String entranceFee;

    private String parking; //주차안내

    private String parkingImg;  //주차안내 사진진

    private String intro;   //한줄소개

    private String observeType;    //관측지 타입(천문대,등등), 추후 enum으로 수정가능?
    private String outline; //개요

}
