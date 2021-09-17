package com.server.tourApiProject.bigPost.post;

import com.server.tourApiProject.bigPost.postHashTag.PostHashTag;
import com.server.tourApiProject.bigPost.postHashTag.PostHashTagRepository;
import com.server.tourApiProject.bigPost.postImage.PostImage;
import com.server.tourApiProject.bigPost.postImage.PostImageRepository;
import com.server.tourApiProject.observation.Observation;
import com.server.tourApiProject.observation.ObservationRepository;
import com.server.tourApiProject.user.User;
import com.server.tourApiProject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ObservationRepository observationRepository;
    private final PostImageRepository postImageRepository;
    private final PostHashTagRepository postHashTagRepository;

    public Post getPost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(IllegalAccessError::new);
        return post;
    }

    public Long createPost(String observePointName, PostParams postParams) {
        Post post = new Post();
        Observation observation = observationRepository.findByObservationName(observePointName);
        Long observationId = observation.getObservationId();
        post.setPostContent(postParams.getPostContent());
        post.setPostTitle(postParams.getPostTitle());
        post.setOptionHashTag(postParams.getOptionHashTag());
        post.setOptionHashTag2(postParams.getOptionHashTag2());
        post.setOptionHashTag3(postParams.getOptionHashTag3());
        post.setOptionHashTag4(postParams.getOptionHashTag4());
        post.setOptionHashTag5(postParams.getOptionHashTag5());
        post.setOptionHashTag6(postParams.getOptionHashTag6());
        post.setOptionHashTag7(postParams.getOptionHashTag7());
        post.setOptionHashTag8(postParams.getOptionHashTag8());
        post.setOptionHashTag9(postParams.getOptionHashTag9());
        post.setOptionHashTag10(postParams.getOptionHashTag10());
        post.setOptionObservation(postParams.getOptionObservation());
        post.setYearDate(postParams.getYearDate());
        post.setTime(postParams.getTime());
        post.setUser(userRepository.findById(postParams.getUserId()).orElseThrow(IllegalAccessError::new));
        post.setUserId(postParams.getUserId());
        post.setObservation(observation);
        post.setObservationId(observationId);
        postRepository.save(post);
        return post.getPostId();
    }

    public List<PostParams2> getMyPost3(Long userId) {
        List<PostParams2> result = new ArrayList<>();
        List<Post> list = postRepository.findByUserId(userId);
        List<Post> three = new ArrayList<>();
        int len = list.size();
        if (len > 3){
            for (int i=len-1; i>len-4; i--){
                three.add(list.get(i));
            }
        } else {
            three = list;
        }

        for (Post post : three){
            PostParams2 postParams2 = new PostParams2();
            postParams2.setTitle(post.getPostTitle());
            List<PostImage> imageList = postImageRepository.findByPostId(post.getPostId());
            if (!imageList.isEmpty()) {
                PostImage postImage = imageList.get(0);
                postParams2.setThumbnail(postImage.getImageName());
            } else{
                postParams2.setThumbnail(null);
            }

            result.add(postParams2);
        }
        return result;
    }
    public void deletePost(Long userId){
        postRepository.deleteById(userId);
    }

    public List<PostParams3> getMyPost(Long userId) {
        List<PostParams3> result = new ArrayList<>();
        List<Post> posts = postRepository.findByUserId(userId);
        for (Post post : posts){
            PostParams3 postParams3 = new PostParams3();
            postParams3.setPostId(post.getPostId());

            List<PostImage> imageList = postImageRepository.findByPostId(post.getPostId());
            if (!imageList.isEmpty()) {
                PostImage postImage = imageList.get(0);
                postParams3.setThumbnail(postImage.getImageName());
            } else{
                postParams3.setThumbnail(null);
            }
            postParams3.setTitle(post.getPostTitle());
            Optional<User> userOp = userRepository.findById(post.getUserId());
            if (userOp.isPresent()){
                User user = userOp.get();
                postParams3.setNickName(user.getNickName());
                postParams3.setProfileImage(user.getProfileImage());
            }
            List<String> hashTagName = new ArrayList<>();
            List<PostHashTag> list = postHashTagRepository.findByPostId(post.getPostId());
            for(PostHashTag postHashTag : list){
                hashTagName.add(postHashTag.getHashTagName());
            }
            postParams3.setHashTagNames(hashTagName);

            result.add(postParams3);
        }
        return result;
    }

    public List<PostParams4>getMainPost(){
        List<PostParams4> result = new ArrayList<>();
        List<Post> posts = postRepository.findAll();
        for (Post post : posts){
            PostParams4 postParams4 = new PostParams4();
            postParams4.setPostId(post.getPostId());
            postParams4.setObservationId(post.getObservationId());
            postParams4.setMainTitle(post.getPostTitle());
            Optional<User> userOp = userRepository.findById(post.getUserId());
            if (userOp.isPresent()){
                User user = userOp.get();
                postParams4.setMainNickName(user.getNickName());
                postParams4.setProfileImage(user.getProfileImage());
            }
            postParams4.setMainObservation(post.getObservation().getObservationName());
            postParams4.setOptionObservation(post.getOptionObservation());
            List<PostImage> mainImageList = postImageRepository.findByPostId(post.getPostId());
            if (!mainImageList.isEmpty()) {
                ArrayList<String> imageList = new ArrayList<>();
                for (int i=0;i<mainImageList.size();i++){
                imageList.add("https://starry-night.s3.ap-northeast-2.amazonaws.com/postImage/"+mainImageList.get(i).getImageName());}
                postParams4.setImages(imageList);
            } else{
                postParams4.setImages(null);
            }
            List<String> mainHashTagName = new ArrayList<>();
            List<PostHashTag> list = postHashTagRepository.findByPostId(post.getPostId());
            if (!list.isEmpty()){
            for(PostHashTag postHashTag : list){
                mainHashTagName.add(postHashTag.getHashTagName());
            }postParams4.setHashTags(mainHashTagName);
            }else{
                postParams4.setHashTags(null);
                postParams4.setOptionHashTag(post.getOptionHashTag());
                postParams4.setOptionHashTag2(post.getOptionHashTag2());
                postParams4.setOptionHashTag3(post.getOptionHashTag3());
            }
            result.add(postParams4);
        }
        return result;
    }
}
