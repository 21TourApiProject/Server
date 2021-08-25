package com.server.tourApiProject.observation.observeHashTag;

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
@Table(name="observeHashTag")
public class ObserveHashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long observeHashTagListId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "observationId", insertable = false, updatable=false)
    private Observation observation;

    @Column(nullable = false)
    private Long observationId;

    @Column(nullable = false)
    private Long hashTagId;

    @Column(nullable = false)
    private String hashTagName;
}
