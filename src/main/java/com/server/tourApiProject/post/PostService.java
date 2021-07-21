package com.server.tourApiProject.post;

import com.server.tourApiProject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void createPost(PostParams postParam) {
        Post post = new Post();
        post.setPostId(postParam.getPostId());
        post.setContent(postParam.getContent());
        post.setRegisterDt(LocalDateTime.now());
        post.setUser(userRepository.getOne(postParam.getUserId()));
        post.setUserId(postParam.getUserId());
        postRepository.save(post);
    }
}
