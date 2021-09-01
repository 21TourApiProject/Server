package com.server.tourApiProject.observation.observeFee;

import com.server.tourApiProject.hashTag.HashTagRepository;
import com.server.tourApiProject.observation.ObservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ObserveFeeService {
    private final ObserveFeeRepository observeFeeRepository;
    private final ObservationRepository observationRepository;

    public List<ObserveFee> getObserveFees(Long observationId) {
        List<ObserveFee> observeFeeList = observeFeeRepository.findByObservationId(observationId);
        return observeFeeList;
    }

}
