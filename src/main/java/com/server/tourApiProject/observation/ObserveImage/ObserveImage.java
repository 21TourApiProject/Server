package com.server.tourApiProject.observation.ObserveImage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.tourApiProject.bigPost.post.Post;
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

    @Column(nullable = false)
    private String image;
}
