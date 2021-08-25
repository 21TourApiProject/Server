package com.server.tourApiProject.touristPoint.nearTouristData;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NearTouristDataParams {

    private Long contentId;
    private String firstImage;
    private String title;
    private String addr1;
    private String cat3Name;
    private String overviewSim;
}
