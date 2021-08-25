package com.server.tourApiProject.observation.observeHashTag;

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
public class ObserveHashTagService {
    private final ObserveHashTagRepository observeHashTagRepository;
    private final ObservationRepository observationRepository;
    private final HashTagRepository hashTagRepository;

    public List<String> getObserveHashTag(Long observationId) {
        List<String> observeHashTagNameList = new ArrayList<>();
        List<ObserveHashTag> observeHashTagList = observeHashTagRepository.findByObservationId(observationId);
        for(ObserveHashTag p : observeHashTagList) {
            observeHashTagNameList.add(p.getHashTagName());
        }
        return observeHashTagNameList;
    }

}
