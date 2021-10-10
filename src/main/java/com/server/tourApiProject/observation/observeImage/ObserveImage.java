package com.server.tourApiProject.observation.observeImage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.tourApiProject.observation.Observation;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "observeImage")
public class ObserveImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long observeImageListId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "observationId", insertable = false, updatable=false)
    private Observation observation;

    @Column(nullable = false)
    private Long observationId;

    @Column(columnDefinition = "TEXT")
    private String image;   // 이미지 경로

    @Column(columnDefinition = "TEXT")
    private String imageSource; //이미지 출저

}
