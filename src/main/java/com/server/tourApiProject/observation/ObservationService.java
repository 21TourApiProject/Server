package com.server.tourApiProject.observation;

import com.server.tourApiProject.hashTag.HashTag;
import com.server.tourApiProject.hashTag.HashTagRepository;
import com.server.tourApiProject.observation.observeHashTag.ObserveHashTag;
import com.server.tourApiProject.observation.observeHashTag.ObserveHashTagParams;
import com.server.tourApiProject.observation.observeHashTag.ObserveHashTagRepository;
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
    private final ObserveHashTagRepository observeHashTagRepository;
    private final HashTagRepository hashTagRepository;

    public List<Observation> getAllObservation() {
        return observationRepository.findAll();
    }

    public void createObservation(ObservationParams observationParams) {
        Observation observation = new Observation();
        observation.setObservationName(observationParams.getObservationName());
        observation.setLink(observationParams.getLink());
        observation.setLatitude(observationParams.getLatitude());
        observation.setLongitude(observationParams.getLongitude());
        observation.setAddress(observationParams.getAddress());
        observation.setPhoneNumber(observationParams.getPhoneNumber());
        observation.setOperatingHour(observationParams.getOperatingHour());
        observation.setParking(observationParams.getParking());
        observation.setObserveType(observationParams.getObserveType());
        observation.setOutline(observationParams.getOutline());
        observationRepository.save(observation);
    }

    public void createObserveHashTags(Long observationId, List<ObserveHashTagParams> observeHashTagParams) {
        Observation observation = observationRepository.findById(observationId).orElseThrow(IllegalAccessError::new);

        for(ObserveHashTagParams p : observeHashTagParams) {
            ObserveHashTag observeHashTag = new ObserveHashTag();
            HashTag hashTag = hashTagRepository.findByHashTagName(p.getHashTagName());
            observeHashTag.setHashTagId(hashTag.getHashTagId());
            observeHashTag.setHashTagName(p.getHashTagName());
            observeHashTag.setObservation(observation);
            observeHashTag.setObservationId(observationId);
            observeHashTagRepository.save(observeHashTag);
        }

    }

    public Observation getObservation(Long observationId){
        Observation observation = observationRepository.findById(observationId).orElseThrow(IllegalAccessError::new);
        return observation;
    }



}
