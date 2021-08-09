package com.server.tourApiProject.postObservePoint;

import com.server.tourApiProject.observePoint.ObservePoint;
import com.server.tourApiProject.observePoint.ObservePointRepository;
import com.server.tourApiProject.post.PostRepository;
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
    private final ObservePointRepository observePointRepository;
    public PostObservePoint getPostObservePoint(Long PostObservePointId){
        PostObservePoint postObservePoint = postObservePointRepository.findById(PostObservePointId).orElseThrow(IllegalAccessError::new);
        return postObservePoint;
    }
    public void createPostObservePoint(PostObservePointParams postObservePointParams) {
        PostObservePoint postObservePoint = new PostObservePoint();
        postObservePoint.setObservePointName(postObservePointParams.getObservePointName());
        ObservePoint observePoint = observePointRepository.findByObservePointName(postObservePoint.getObservePointName());
        postObservePoint.setObservePointId(observePoint.getObservePointId());
        postObservePointRepository.save(postObservePoint);
    }
}

