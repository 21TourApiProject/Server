package com.server.tourApiProject.star.constellation;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConstellationParams {
    private String constName;
    private Long constId;
    private String constEng;
}