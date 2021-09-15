package com.server.tourApiProject.touristPoint.nearTouristData;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NearTouristDataParams {

    private Long contentId;
    private String firstImage;
    private String title;
    private String addr;
    private String cat3Name;
    private String overviewSim;
    private List<String> hashTagNames;
}
