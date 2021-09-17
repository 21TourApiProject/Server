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

    public void createPostImage(Long postId,List<PostImageParams> postImageParams) {
        for (PostImageParams p : postImageParams) {
            Post post = postRepository.findById(postId).orElseThrow(IllegalAccessError::new);
            PostImage postImage = new PostImage();
            postImage.setImageName(p.getImageName());
            postImage.setPost(post);
            postImage.setPostId(post.getPostId());

            postImageRepository.save(postImage);
        }
    }
    public List<PostImage> getRelatePostImageList(Long observationId){
        List<PostImage> postImageList = new ArrayList<>();
        List<Post> posts = postRepository.findByObservationId(observationId);
        for (Post post : posts){
            PostImage postImage = new PostImage();
            postImage.setPost(post);
            postImage.setPostId(post.getPostId());
            postImage.setImageName(post.getPostImages().get(0).getImageName());
            postImageList.add(postImage);
        }
        return postImageList;
    }
    public void deletePostImage(){postImageRepository.deleteAll();}
}
