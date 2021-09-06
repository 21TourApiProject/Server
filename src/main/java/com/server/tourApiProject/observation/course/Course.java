package com.server.tourApiProject.observation.course;

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
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false)
    private int courseOrder;   //코스안에서 관광지 순서

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "observationId", insertable = false, updatable=false)
    private Observation observation;

    @Column(nullable = false)
    private Long observationId;

    @Column(nullable = false)
    private Long touristPointId;

}
