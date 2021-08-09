package com.server.tourApiProject.post;

import com.server.tourApiProject.postObservePoint.PostObservePointRepository;
import com.server.tourApiProject.user.UserRepository;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostObservePointRepository postObservePointRepository;

    public Post getPost(Long postId){
        Post post=  postRepository.findById(postId).orElseThrow(IllegalAccessError::new);
        return post;
    }

    public void createPost(PostParams postParams) {
        Post post = new Post();
        post.setPostContent(postParams.getPostContent());
        post.setYearDate(postParams.getYearDate());
        post.setTime(postParams.getTime());
        post.setUser(userRepository.getOne(postParams.getUserId()));
        post.setUserId(postParams.getUserId());
        post.setPostObservePoint(postObservePointRepository.getOne(postParams.getPostObservePointId()));
        post.setPostObservePointId(postParams.getPostObservePointId());
        postRepository.save(post);
    }
}
