package com.server.tourApiProject.touristPoint.touristDataHashTag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.tourApiProject.touristPoint.touristData.TouristData;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="touristDataHashTag")
public class TouristDataHashTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long touristDataHashTagId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contentId", insertable = false, updatable=false)
    private TouristData touristData;

    @Column(nullable = false)
    private Long contentId;

    @Column(nullable = false)
    private Long hashTagId;

    @Column(nullable = false)
    private String hashTagName;
}
