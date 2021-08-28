package com.server.tourApiProject.observation.course;

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
    private Long courseOrder;   //코스안에서 관광지 순서

    @Column(nullable = false)
    private Long observationId;

    @Column(nullable = false)
    private Long touristPointId;

}
