package com.server.tourApiProject.bigPost.postObservePoint;

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
    public PostObservePoint getPostObservePoint(Long PostObservePointId){
        PostObservePoint postObservePoint = postObservePointRepository.findById(PostObservePointId).orElseThrow(IllegalAccessError::new);
        return postObservePoint;
    }
    public void createPostObservePoint(PostObservePointParams postObservePointParams) {
        PostObservePoint postObservePoint = new PostObservePoint();
        postObservePoint.setObservePointName(postObservePointParams.getObservePointName());
        postObservePointRepository.save(postObservePoint);
    }
}

