package com.server.tourApiProject.star.Horoscope;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "horoscope")
public class Horoscope {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long horId;

    @Column(nullable = false)
    private String horImage;  //별자리 이미지

    @Column(nullable = false)
    private String horEngTitle;  //별자리 영어 이름

    @Column(nullable = false)
    private String horKrTitle;  //별자리 한국 이름

    @Column(nullable = false)
    private String horPeriod;   //별자리 기간

    @Column(nullable = false, columnDefinition = "TEXT")
    private String horDesc1;    //1월 별자리 운세

    @Column(nullable = false, columnDefinition = "TEXT")
    private String horDesc2;    //2월 별자리 운세

    @Column(nullable = false, columnDefinition = "TEXT")
    private String horDesc3;    //3월 별자리 운세

    @Column(nullable = false, columnDefinition = "TEXT")
    private String horDesc4;    //4월 별자리 운세

    @Column(nullable = false, columnDefinition = "TEXT")
    private String horDesc5;    //5월 별자리 운세

    @Column(nullable = false, columnDefinition = "TEXT")
    private String horDesc6;    //6월 별자리 운세

    @Column(nullable = false, columnDefinition = "TEXT")
    private String horDesc7;    //7월 별자리 운세

    @Column(nullable = false, columnDefinition = "TEXT")
    private String horDesc8;    //8월 별자리 운세

    @Column(nullable = false, columnDefinition = "TEXT")
    private String horDesc9;    //9월 별자리 운세

    @Column(nullable = false, columnDefinition = "TEXT")
    private String horDesc10;   //10월 별자리 운세

    @Column(nullable = false, columnDefinition = "TEXT")
    private String horDesc11;   //11월 별자리 운세

    @Column(nullable = false, columnDefinition = "TEXT")
    private String horDesc12;   //12월 별자리 운세
}
