package com.server.tourApiProject.touristPoint.touristDataHashTag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TouristDataHashTagService {

    private final TouristDataHashTagRepository touristDataHashTagRepository;

    public void createTouristDataHashTag(TouristDataHashTag touristDataHashTag) {
        touristDataHashTagRepository.save(touristDataHashTag);
    }
}
