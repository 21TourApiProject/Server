package com.server.tourApiProject.observePoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ObservePointService {
    private final ObservePointRepository observePointRepository;

    public List<ObservePoint> getAllObservePoint() {
        return observePointRepository.findAll();
    }

    public void createObservePoint(ObservePoint observePoint) {
        observePointRepository.save(observePoint);
    }
}
