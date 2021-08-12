package com.server.tourApiProject.bigPost.postImage;

import com.server.tourApiProject.bigPost.post.Post;
import com.server.tourApiProject.bigPost.post.PostRepository;
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
public class PostImageService {
    private final PostImageRepository postImageRepository;
    private final PostRepository postRepository;


    public List<String> getPostImage(Long postId) {
        List<String> postImageNameList =new ArrayList<>();
        List<PostImage> postImageList = postImageRepository.findByPostId(postId);
        for(PostImage p : postImageList) {
            postImageNameList.add(p.getImageName());
        }
        return postImageNameList;
    }
    public String getPostImageName(Long postImageListId){
        PostImage postImage = postImageRepository.findByPostImageListId(postImageListId);
        return postImage.getImageName();
    }

    public void createPostImage(String postContent,List<PostImageParams> postImageParams) {
        Post post = postRepository.findByPostContent(postContent);
        Long postId = post.getPostId();
        for (PostImageParams p : postImageParams) {
            PostImage postImage = new PostImage();
            postImage.setImageName(p.getImageName());
            postImage.setPost(post);
            postImage.setPostId(postId);

            postImageRepository.save(postImage);
        }
    }
}
