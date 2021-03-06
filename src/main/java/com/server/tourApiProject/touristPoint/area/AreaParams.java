package com.server.tourApiProject.touristPoint.area;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AreaParams {
    Long code1;
    String name1;
    Long code2;
    String name2;
}
