package com.server.tourApiProject.observation.ObserveImage;

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
public class ObserveImageService {
    private final ObserveImageRepository observeImageRepository;
    private final PostRepository postRepository;


    public List<String> getPostImage(Long postId) {
        List<String> postImageNameList =new ArrayList<>();
        List<ObserveImage> observeImageList = observeImageRepository.findByPostId(postId);
        for(ObserveImage p : observeImageList) {
            postImageNameList.add(p.getImageName());
        }
        return postImageNameList;
    }
    public String getPostImageName(Long postImageListId){
        ObserveImage observeImage = observeImageRepository.findByPostImageListId(postImageListId);
        return observeImage.getImageName();
    }

    public void createPostImage(String postContent,List<ObserveImageParams> observeImageParams) {
        Post post = postRepository.findByPostContent(postContent);
        Long postId = post.getPostId();
        for (ObserveImageParams p : observeImageParams) {
            ObserveImage observeImage = new ObserveImage();
            observeImage.setImageName(p.getImageName());
            observeImage.setPost(post);
            observeImage.setPostId(postId);

            observeImageRepository.save(observeImage);
        }
    }
}
