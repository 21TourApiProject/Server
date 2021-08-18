package com.server.tourApiProject.constellation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.tourApiProject.tonightSky.TonightSky;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "constellation")
public class Constellation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long constId;

    @Column(nullable = false)
    private String constName;  //별자리 이름

    @Column(nullable = false)
    private String constImage;  //별자리 이미지

    @Column(nullable = false)
    private String constStory;    // 별자리 설화

    @Column(nullable = false)
    private String springConstMtd; // 봄 별자리 관측법

    @Column(nullable = false)
    private String summerConstMtd; // 여름 별자리 관측법

    @Column(nullable = false)
    private String fallConstMtd; // 가을 별자리 관측법

    @Column(nullable = false)
    private String winterConstMtd; // 갸울 별자리 관측법

    @Column(nullable = false)
    private String constBestMonth; // 가장 보기 좋은 달

    private String constPersonality; // 별자리 성격

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate startDate;    // 별자리가 보이기 시작하는 날짜

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate endDate;    // 별자리가 보이기 끝나는 날짜


//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "skyId", insertable = false, updatable = false)
//    private TonightSky tonightSky; //오늘의 밤하늘 id
}
