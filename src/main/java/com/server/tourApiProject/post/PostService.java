package com.server.tourApiProject.post;

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
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post getPost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(IllegalAccessError::new);
        return post;
    }

    public void createPost(PostParams postParams) {
        Post post = new Post();
        post.setPostContent(postParams.getPostContent());
        post.setObserveFit(postParams.getObserveFit());
        post.setYearDate(postParams.getYearDate());
        post.setTime(postParams.getTime());
        post.setUser(userRepository.getOne(postParams.getUserId()));
        post.setUserId(postParams.getUserId());
        postRepository.save(post);
    }


    public List<PostParams2> getMyPosts(Long userId) {
        List<PostParams2> result = new ArrayList<>();
        List<Post> posts = postRepository.findUserById(userId);
        for (Post post : posts){
            PostParams2 postParams2 = new PostParams2();
            postParams2.setPostId(post.getPostId());
            postParams2.setThumbnail(""); //추후 첫번째 이미지로 수정
            postParams2.setTitle(post.getPostContent()); //추후 제목으로 수정
            result.add(postParams2);
        }
        return result;
    }
}
