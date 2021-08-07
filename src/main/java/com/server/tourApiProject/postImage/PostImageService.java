package com.server.tourApiProject.postImage;

import com.server.tourApiProject.post.Post;
import com.server.tourApiProject.post.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostImageService {
    private final PostImageRepository postImageRepository;
    private final PostRepository postRepository;

    public List<PostImage> getImage(Long postId) {
        return postImageRepository.findByPostId(postId);
    }

    public void createPostImage(List<PostImageParams> postImageParams) {
        for (PostImageParams p : postImageParams) {
            PostImage postImage = new PostImage();
            postImage.setImageName(p.getImageName());

            Post post = postRepository.findById(p.getPostId()).orElseThrow(IllegalAccessError::new);
            postImage.setPost(post);
            postImage.setPostId(post.getPostId());

            postImageRepository.save(postImage);
        }
    }
}
