package com.server.tourApiProject.weather.WtToday;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wtToday")
public class WtToday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wtTodayId;

    @Column(nullable = false)
    private String todayWtId;    //오늘의 날씨 아이디

    @Column(nullable = false)
    private String todayWtName1;    //오늘의 날씨 이름

    private String todayWtName2;    //오늘의 날씨 이름(밑에 줄)
}
