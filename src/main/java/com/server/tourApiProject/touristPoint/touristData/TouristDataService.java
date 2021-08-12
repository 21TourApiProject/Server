package com.server.tourApiProject.touristPoint.touristData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TouristDataService {

    private final TouristDataRepository touristDataRepository;
    public void createTouristData(TouristData touristData) {
        touristDataRepository.save(touristData);
    }
}
