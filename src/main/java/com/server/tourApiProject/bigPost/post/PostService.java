package com.server.tourApiProject.bigPost.post;

import com.server.tourApiProject.bigPost.postHashTag.PostHashTag;
import com.server.tourApiProject.bigPost.postHashTag.PostHashTagRepository;
import com.server.tourApiProject.bigPost.postImage.PostImage;
import com.server.tourApiProject.bigPost.postImage.PostImageRepository;
import com.server.tourApiProject.myHashTag.MyHashTag;
import com.server.tourApiProject.myHashTag.MyHashTagRepository;
import com.server.tourApiProject.observation.Observation;
import com.server.tourApiProject.observation.ObservationRepository;
import com.server.tourApiProject.search.Filter;
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
    private final MyHashTagRepository myHashTagRepository;

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
            int i = 0;
            for(PostHashTag postHashTag : list){
                if(i > 3)
                    break;
                hashTagName.add(postHashTag.getHashTagName());
                i++;
            }
            postParams3.setHashTagNames(hashTagName);

            result.add(postParams3);
        }
        return result;
    }

    public List<PostParams5> getRelatePost(Long observationId) {
        List<PostParams5> result1 = new ArrayList<>();
        List<Post> relatePosts = postRepository.findByObservationId(observationId);
        for (Post relatePost : relatePosts){
            PostParams5 postParams5 = new PostParams5();
            postParams5.setPostId(relatePost.getPostId());

            List<PostImage> imageList = postImageRepository.findByPostId(relatePost.getPostId());
            if (!imageList.isEmpty()) {
                PostImage postImage = imageList.get(0);
                postParams5.setThumbnail(postImage.getImageName());
            } else{
                postParams5.setThumbnail(null);
            }
            postParams5.setTitle(relatePost.getPostTitle());
            Optional<User> userOp = userRepository.findById(relatePost.getUserId());
            if (userOp.isPresent()){
                User user = userOp.get();
                postParams5.setNickName(user.getNickName());
                postParams5.setProfileImage(user.getProfileImage());
            }
            List<String> hashTagName = new ArrayList<>();
            List<PostHashTag> list = postHashTagRepository.findByPostId(relatePost.getPostId());
            for(PostHashTag postHashTag : list){
                hashTagName.add(postHashTag.getHashTagName());
            }
            postParams5.setHashTagNames(hashTagName);

            result1.add(postParams5);
        }
        return result1;
    }

    public List<PostParams4>getMainPost(Filter filter) {
        List<PostParams4> result = new ArrayList<>();
        List<Long> mainHashTagIdList = filter.getHashTagIdList();
        List<Long> mainPostIdList = new ArrayList<>();
        for (Long hashTagId : mainHashTagIdList) {
            List<PostHashTag> postHashTagList = postHashTagRepository.findByHashTagId(hashTagId);
            for (PostHashTag postHashTag : postHashTagList) {
                Long postId = postHashTag.getPostId();
                if (!mainPostIdList.contains(postId)) {
                    mainPostIdList.add(postId);
                    if (mainPostIdList.size()>6)break;
                }
            }
        }
        //나머지 게시물
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            if (!mainPostIdList.contains(post.getPostId())){
                PostParams4 postParams4 = new PostParams4();
                postParams4.setPostId(post.getPostId());
                postParams4.setObservationId(post.getObservationId());
                postParams4.setMainTitle(post.getPostTitle());
                Optional<User> userOp = userRepository.findById(post.getUserId());
                if (userOp.isPresent()) {
                    User user = userOp.get();
                    postParams4.setMainNickName(user.getNickName());
                    postParams4.setProfileImage(user.getProfileImage());
                }
                postParams4.setMainObservation(post.getObservation().getObservationName());
                postParams4.setOptionObservation(post.getOptionObservation());
                List<PostImage> mainImageList = postImageRepository.findByPostId(post.getPostId());
                if (!mainImageList.isEmpty()) {
                    ArrayList<String> imageList = new ArrayList<>();
                    for (int i = 0; i < mainImageList.size(); i++) {
                        imageList.add("https://starry-night.s3.ap-northeast-2.amazonaws.com/postImage/" + mainImageList.get(i).getImageName());
                    }
                    postParams4.setImages(imageList);
                } else {
                    postParams4.setImages(null);
                }
                List<String> mainHashTagName = new ArrayList<>();
                List<PostHashTag> list = postHashTagRepository.findByPostId(post.getPostId());
                if (!list.isEmpty()) {
                    for (PostHashTag postHashTag : list) {
                        mainHashTagName.add(postHashTag.getHashTagName());
                    }
                    postParams4.setHashTags(mainHashTagName);
                } else {
                    postParams4.setHashTags(null);
                    postParams4.setOptionHashTag(post.getOptionHashTag());
                    postParams4.setOptionHashTag2(post.getOptionHashTag2());
                    postParams4.setOptionHashTag3(post.getOptionHashTag3());
                }
                result.add(postParams4);
            }
        }
        for (Long postId : mainPostIdList){
            Post hashPost = postRepository.findById(postId).orElseThrow(IllegalAccessError::new);
            PostParams4 hashPostParams = new PostParams4();
            hashPostParams.setPostId(hashPost.getPostId());
            hashPostParams.setMainTitle(hashPost.getPostTitle());
            Optional<User> userOp = userRepository.findById(hashPost.getUserId());
            if (userOp.isPresent()){
                User user = userOp.get();
                hashPostParams.setMainNickName(user.getNickName());
                hashPostParams.setProfileImage(user.getProfileImage());
            }
            hashPostParams.setMainObservation(hashPost.getObservation().getObservationName());
            hashPostParams.setOptionObservation(hashPost.getOptionObservation());
            List<String> hashTagName = new ArrayList<>();
            List<PostHashTag> list = postHashTagRepository.findByPostId(hashPost.getPostId());
            if (!list.isEmpty()) {
                for (PostHashTag postHashTag : list) {
                    hashTagName.add(postHashTag.getHashTagName());
                }
                hashPostParams.setHashTags(hashTagName);
            } else {
                hashPostParams.setHashTags(null);
                hashPostParams.setOptionHashTag(hashPost.getOptionHashTag());
                hashPostParams.setOptionHashTag2(hashPost.getOptionHashTag2());
                hashPostParams.setOptionHashTag3(hashPost.getOptionHashTag3());
            }
            hashPostParams.setHashTags(hashTagName);
            List<PostImage> hashImageList = postImageRepository.findByPostId(hashPost.getPostId());
            if (!hashImageList.isEmpty()) {
                ArrayList<String> imageList = new ArrayList<>();
                for (int i = 0; i < hashImageList.size(); i++) {
                    imageList.add("https://starry-night.s3.ap-northeast-2.amazonaws.com/postImage/" + hashImageList.get(i).getImageName());
                }
                hashPostParams.setImages(imageList);
            } else {
                hashPostParams.setImages(null);
            }
            result.add(hashPostParams);
        }

            return result;
        }

    public List<PostParams6>getPostDataWithFilter(Filter filter, String searchKey){
        List<Long> areaCodeList = filter.getAreaCodeList();
        List<Long> hashTagIdList= filter.getHashTagIdList();//해시태그 리스트
        List<PostParams6>result = new ArrayList<>();
        List<Long>postIdList = new ArrayList<>();
        List<Long> filterIdList = new ArrayList<>();
        List<Post> searchList = new ArrayList<>();
        List<Post> keyList = new ArrayList<>();
        for (Long hashTagId: hashTagIdList){
            List<PostHashTag> postHashTagList= postHashTagRepository.findByHashTagId(hashTagId);
            for (PostHashTag postHashTag : postHashTagList){
                Long postId = postHashTag.getPostId();
                if (!postIdList.contains(postId)){
                    postIdList.add(postId);
                }
            }
        }
        if (!areaCodeList.isEmpty()) {
            for (Long areaCode : areaCodeList) {
                List<Post> postList = postRepository.findByAreaCode(areaCode);
                //관측지에 아직 지역코드 추가 안해서 아래줄로 대체해 놓았음
//                List<Observation> observationList = observationRepository.findAll();
                if (hashTagIdList.isEmpty()) {
                    //해쉬태그 없으면 지역결과 전부추가
                    for (Post post : postList) {
                        filterIdList.add(post.getPostId());
                    }
                } else {
                    //해쉬태그 있으면 필터 중첩
                    for (Post post : postList) {
                        Long postId = post.getPostId();
                        if (postIdList.contains(postId)) {
                            //해시태그결과에서 지역 있으면 filter최종결과에 포함
                            filterIdList.add(postId);
                        }
                    }
                }
            }
        } else {
            //area 비어있으면
            filterIdList = postIdList;
        }

        searchList = postRepository.findByPostTitleAndPostContent(searchKey,searchKey);
        keyList = postRepository.findByPostTitleAndPostContent(searchKey,searchKey);
        if (!hashTagIdList.isEmpty()||!areaCodeList.isEmpty()) {
            //필터 받은게 없으면 그냥 검색결과 전달, 있으면 중첩 검색
            for (Post post : keyList) {
                //전체 검색어 결과 돌면서
                if (!filterIdList.contains(post.getPostId())) {
                    //필터결과에 검색어 결과 없으면 필터+검색어검색결과에서 삭제
                    searchList.remove(post);
                }
            }
        }
        for (Post post : searchList){
            PostParams6 postParams6 = new PostParams6();
            postParams6.setPostId(post.getPostId());
            postParams6.setTitle(post.getPostTitle());
            Optional<User> userOp = userRepository.findById(post.getUserId());
            if (userOp.isPresent()){
                User user = userOp.get();
                postParams6.setNickName(user.getNickName());
                postParams6.setProfileImage(user.getProfileImage());
            }
            List<String> hashTagName = new ArrayList<>();
            List<PostHashTag> list = postHashTagRepository.findByPostId(post.getPostId());
            for(PostHashTag postHashTag : list){
                hashTagName.add(postHashTag.getHashTagName());
            }
            postParams6.setHashTagNames(hashTagName);
            List<PostImage> imageList = postImageRepository.findByPostId(post.getPostId());
            if (!imageList.isEmpty()) {
                PostImage postImage = imageList.get(0);
                postParams6.setThumbnail(postImage.getImageName());
            } else{
                postParams6.setThumbnail(null);
            }
            result.add(postParams6);
        }
        return result;
    }
}
