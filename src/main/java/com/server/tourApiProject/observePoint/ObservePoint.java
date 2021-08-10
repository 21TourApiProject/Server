package com.server.tourApiProject.observePoint;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="observePoint")
public class ObservePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long observePointId;

    @Column(nullable = false, unique = true)
    private String observePointName;
}
