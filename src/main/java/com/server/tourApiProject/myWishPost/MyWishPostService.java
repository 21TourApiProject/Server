package com.server.tourApiProject.myWishPost;

import com.server.tourApiProject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MyWishPostService {
    private final UserRepository userRepository;
    private final MyWishPostRepository myWishPostRepository;

    public void createMyWishPost(Long userId, Long postId) {
        MyWishPost myWishPost = new MyWishPost();
        myWishPost.setUserId(userId);
        myWishPost.setPostId(postId);
        myWishPost.setUser(userRepository.findById(userId).orElseThrow(IllegalAccessError::new));

        myWishPostRepository.save(myWishPost);
    }
}
