package com.server.tourApiProject.myWishPost;

import com.server.tourApiProject.post.PostRepository;
import com.server.tourApiProject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MyWishPostService {
    private final UserRepository userRepository;
    private final MyWishPostRepository myWishPostRepository;
    private final PostRepository postRepository;

    public void createMyWishPost(Long userId, Long postId) {
        postRepository.findById(postId).orElseThrow(IllegalAccessError::new); //postId에 해당하는 게시물이 없으면 오류 발생
        MyWishPost myWishPost = new MyWishPost();
        myWishPost.setUserId(userId);
        myWishPost.setPostId(postId);
        myWishPost.setUser(userRepository.findById(userId).orElseThrow(IllegalAccessError::new));

        myWishPostRepository.save(myWishPost);
    }

//    public Map<String, String> getMyWishPosts(Long userId) {
//        List<MyWishPost> myWishPosts = myWishPostRepository.findByUserId(userId);
//
//
//    }
}
