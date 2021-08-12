package com.server.tourApiProject.bigPost.postObservePoint;

import com.server.tourApiProject.observation.Observation;
import com.server.tourApiProject.observation.ObservationRepository;
import com.server.tourApiProject.bigPost.post.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostObservePointService {
    private final PostObservePointRepository postObservePointRepository;
    private final PostRepository postRepository;
    private final ObservationRepository observationRepository;
    public PostObservePoint getPostObservePoint(Long PostObservePointId){
        PostObservePoint postObservePoint = postObservePointRepository.findById(PostObservePointId).orElseThrow(IllegalAccessError::new);
        return postObservePoint;
    }
    public void createPostObservePoint(PostObservePointParams postObservePointParams) {
        PostObservePoint postObservePoint = new PostObservePoint();
        postObservePoint.setObservePointName(postObservePointParams.getObservePointName());
        Observation observation = observationRepository.findByObservationName(postObservePoint.getObservePointName());
        postObservePoint.setObservePointId(observation.getObservationId());
        postObservePointRepository.save(postObservePoint);
    }
}

