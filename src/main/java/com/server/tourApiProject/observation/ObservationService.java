package com.server.tourApiProject.observation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ObservationService {
    private final ObservationRepository observationRepository;

    public List<Observation> getAllObservePoint() {
        return observationRepository.findAll();
    }

    public void createObservePoint(Observation observation) {
        observationRepository.save(observation);
    }
}
