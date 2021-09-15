package com.server.tourApiProject.observation.observeFee;

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
@Table(name="observeFee")
public class ObserveFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long observeFeeListId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "observationId", insertable = false, updatable=false)
    private Observation observation;

    @Column(nullable = false)
    private Long observationId;

    @Column(nullable = false)
    private String feeName;

    @Column
    private String entranceFee;

}
