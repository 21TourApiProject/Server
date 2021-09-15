package com.server.tourApiProject.constellation;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConstellationParams {
    private String constName;
    private String constImage;
    private Long constId;
}