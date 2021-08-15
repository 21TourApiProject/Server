package com.server.tourApiProject.observation.ObserveHashTag;

import com.server.tourApiProject.hashTag.HashTag;
import com.server.tourApiProject.hashTag.HashTagRepository;
import com.server.tourApiProject.observation.Observation;
import com.server.tourApiProject.observation.ObservationRepository;
import com.server.tourApiProject.user.User;
import com.server.tourApiProject.user.UserRepository;
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

//    public Long createObserveHashTags(Long observationId, List<ObserveHashTagParams> observeHashTagParams) {
//        Observation observation = observationRepository.findById(observationId).orElseThrow(IllegalAccessError::new);
//
//        for(ObserveHashTagParams p : observeHashTagParams) {
//            ObserveHashTag observeHashTag = new ObserveHashTag();
//            observeHashTag.setHashTagName(p.getHashTagName());
//            observeHashTag.setUser(user);
//            observeHashTag.setUserId(userId);
//            HashTag hashTag = hashTagRepository.findByHashTagName(p.getHashTagName());
//            observeHashTag.setHashTagId(hashTag.getHashTagId());
//
//            observeHashTagRepository.save(observeHashTag);
//        }
//        return userId;
//    }
}
