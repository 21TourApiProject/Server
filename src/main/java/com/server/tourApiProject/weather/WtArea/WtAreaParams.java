package com.server.tourApiProject.weather.WtArea;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WtAreaParams {
    private Double latitude;
    private Double longitude;

    private Double minLightPol;
    private Double maxLightPol;
}
