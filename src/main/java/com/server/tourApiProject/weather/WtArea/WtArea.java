package com.server.tourApiProject.weather.WtArea;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wtArea")
public class WtArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wtAreaId;

    @Column(nullable = false)
    private String cityName;    //도시 이름

    @Column(nullable = false)
    private String provName;    //지역 이름

    @Column(nullable = false)
    private Double latitude;    //위도

    @Column(nullable = false)
    private Double longitude;    //경도

    @Column(nullable = false)
    private Double minLightPol;    //광공해 최소

    @Column(nullable = false)
    private Double maxLightPol;    //광공해 최대

}
