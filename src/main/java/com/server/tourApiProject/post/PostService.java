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

    public void createPost(PostParams postParams) {
        Post post = new Post();
        post.setPostId(postParams.getPostId());
        post.setRegisterDt(LocalDateTime.now());
        post.setContent(postParams.getContent());
        post.setUser(userRepository.getOne(postParams.getUserId()));
        post.setUserId(postParams.getUserId());
        postRepository.save(post);
    }
}
