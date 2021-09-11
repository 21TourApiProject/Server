package com.server.tourApiProject.weather;

import lombok.*;

import javax.persistence.Column;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WtAreaParams {
    private Double latitude;
    private Double longitude;
}
