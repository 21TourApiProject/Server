package com.server.tourApiProject.post;

import com.server.tourApiProject.user.User;
import com.server.tourApiProject.user.UserRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    public Post getPost(Long postId){
        Post post=  postRepository.findById(postId).orElseThrow(IllegalAccessError::new);
        return post;
    }

    public void createPost(PostParams postParams) {
        Post post = new Post();
        post.setPostId(postParams.getPostId());
        post.setRegisterDt(LocalDateTime.now());
        post.setPostContent(postParams.getPostContent());
        post.setPostImage(postParams.getPostImage());
        post.setObserveFit(postParams.getObserveFit());
        post.setYearDate(postParams.getYearDate());
        post.setTime(postParams.getTime());
        post.setUser(userRepository.getOne(postParams.getUserId()));
        post.setUserId(postParams.getUserId());
        postRepository.save(post);
    }
}
