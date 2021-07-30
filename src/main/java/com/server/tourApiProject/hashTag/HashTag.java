package com.server.tourApiProject.hashTag;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="hashTag")
public class HashTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hashTagId;

    @Column(nullable = false, unique = true)
    private String hashTagName;
}
