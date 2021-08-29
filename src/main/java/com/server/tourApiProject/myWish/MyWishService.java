package com.server.tourApiProject.myWish;

import com.server.tourApiProject.bigPost.post.Post;
import com.server.tourApiProject.bigPost.post.PostRepository;
import com.server.tourApiProject.bigPost.postHashTag.PostHashTag;
import com.server.tourApiProject.bigPost.postHashTag.PostHashTagRepository;
import com.server.tourApiProject.bigPost.postImage.PostImage;
import com.server.tourApiProject.bigPost.postImage.PostImageRepository;
import com.server.tourApiProject.observation.Observation;
import com.server.tourApiProject.observation.ObservationRepository;
import com.server.tourApiProject.observation.observeHashTag.ObserveHashTag;
import com.server.tourApiProject.observation.observeHashTag.ObserveHashTagRepository;
import com.server.tourApiProject.observation.observeImage.ObserveImage;
import com.server.tourApiProject.observation.observeImage.ObserveImageRepository;
import com.server.tourApiProject.touristPoint.touristData.TouristData;
import com.server.tourApiProject.touristPoint.touristData.TouristDataRepository;
import com.server.tourApiProject.touristPoint.touristDataHashTag.TouristDataHashTag;
import com.server.tourApiProject.touristPoint.touristDataHashTag.TouristDataHashTagRepository;
import com.server.tourApiProject.user.User;
import com.server.tourApiProject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MyWishService {
    private final MyWishRepository myWishRepository;
    private final UserRepository userRepository;
    private final ObservationRepository observationRepository;
    private final ObserveImageRepository observeImageRepository;
    private final ObserveHashTagRepository observeHashTagRepository;
    private final TouristDataRepository touristDataRepository;
    private final TouristDataHashTagRepository touristDataHashTagRepository;
    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;
    private final PostHashTagRepository postHashTagRepository;

    public void createMyWish(Long userId, Long itemId, Integer wishType) {
        MyWish myWish = new MyWish();

        switch(wishType) {
            case 0: //관측지
                observationRepository.findById(itemId).orElseThrow(IllegalAccessError::new);  //itemId에 해당하는 관측지 id가 없으면 오류 발생
                myWish.setUserId(userId);
                myWish.setUser(userRepository.findById(userId).orElseThrow(IllegalAccessError::new));
                myWish.setWishType(0); // 관측지
                myWish.setItemId(itemId);
                String now0 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                myWish.setWishTime(Long.parseLong(now0));
                myWishRepository.save(myWish);
                break;
            case 1: //관광지
                touristDataRepository.findById(itemId).orElseThrow(IllegalAccessError::new);  //itemId에 해당하는 관광지 id가 없으면 오류 발생
                myWish.setUserId(userId);
                myWish.setUserId(userId);
                myWish.setUser(userRepository.findById(userId).orElseThrow(IllegalAccessError::new));
                myWish.setWishType(1); // 관광지
                myWish.setItemId(itemId);
                String now1 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                myWish.setWishTime(Long.parseLong(now1));
                myWishRepository.save(myWish);
                break;
            case 2: //게시물
                postRepository.findById(itemId).orElseThrow(IllegalAccessError::new);  //itemId에 해당하는 게시물 id가 없으면 오류 발생
                myWish.setUserId(userId);
                myWish.setUser(userRepository.findById(userId).orElseThrow(IllegalAccessError::new));
                myWish.setWishType(2); // 게시물
                myWish.setItemId(itemId);
                String now2 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                myWish.setWishTime(Long.parseLong(now2));
                myWishRepository.save(myWish);
                break;
        }
    }

    //찜한 관측지 목록 불러오기
    public List<MyWishParams01> getMyWishObservation(Long userId) {
        List<MyWishParams01> result= new ArrayList<>();
        List<MyWish> wish = myWishRepository.findByUserIdAndWishType(userId, 0);
        for (MyWish myWish: wish){
            Long observationId = myWish.getItemId();
            Observation observation = observationRepository.findById(observationId).orElseThrow(IllegalAccessError::new);
            MyWishParams01 myWishParams01 = new MyWishParams01();
            myWishParams01.setItemId(observationId);

            List<ObserveImage> imageList = observeImageRepository.findByObservationId(observationId);
            ObserveImage observeImage = imageList.get(0);
            myWishParams01.setThumbnail(observeImage.getImage());

            myWishParams01.setTitle(observation.getObservationName());
            myWishParams01.setAddress(observation.getAddress());
            myWishParams01.setCat3(observation.getObserveType());
            myWishParams01.setOverviewSim(observation.getOutline());

            List<ObserveHashTag> hashTagList = observeHashTagRepository.findByObservationId(observationId);
            List<Long> hashTags = new ArrayList<>();
            for (ObserveHashTag hashTag : hashTagList){
                hashTags.add(hashTag.getHashTagId());
            }
            myWishParams01.setHashTags(hashTags);

            result.add(myWishParams01);
        }
        return result;
    }

    //찜한 관광지 목록 불러오기
    public List<MyWishParams01> getMyWishTouristPoint(Long userId) {
        List<MyWishParams01> result= new ArrayList<>();
        List<MyWish> wish = myWishRepository.findByUserIdAndWishType(userId, 1);
        for (MyWish myWish: wish){
            Long contentId = myWish.getItemId();
            TouristData touristData = touristDataRepository.findById(contentId).orElseThrow(IllegalAccessError::new);
            MyWishParams01 myWishParams01 = new MyWishParams01();
            myWishParams01.setItemId(contentId);
            myWishParams01.setThumbnail(touristData.getFirstImage());
            myWishParams01.setTitle(touristData.getTitle());
            myWishParams01.setAddress(touristData.getAddr1());
            myWishParams01.setCat3(touristData.getCat3());
            myWishParams01.setOverviewSim(touristData.getOverviewSim());

            List<TouristDataHashTag> hashTagList = touristDataHashTagRepository.findByContentId(contentId);
            List<Long> hashTags = new ArrayList<>();
            for (TouristDataHashTag hashTag : hashTagList){
                hashTags.add(hashTag.getHashTagId());
            }
            myWishParams01.setHashTags(hashTags);

            result.add(myWishParams01);
        }
        return result;
    }


    //찜한 게시물 목록 불러오기
    public List<MyWishParams2> getMyWishPost(Long userId) {
        List<MyWishParams2> result= new ArrayList<>();
        List<MyWish> wish = myWishRepository.findByUserIdAndWishType(userId, 2);
        for (MyWish myWish: wish){
            Long postId = myWish.getItemId();
            Post post = postRepository.findById(postId).orElseThrow(IllegalAccessError::new);
            MyWishParams2 myWishParams2 = new MyWishParams2();
            myWishParams2.setItemId(postId);

            List<PostImage> imageList = postImageRepository.findByPostId(postId);
            PostImage postImage = imageList.get(0);
            myWishParams2.setThumbnail(postImage.getImageName());

            myWishParams2.setTitle(post.getPostTitle());

            User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
            myWishParams2.setWriter(user.getNickName());
            myWishParams2.setProfileImage(user.getProfileImage());

            List<PostHashTag> hashTagList = postHashTagRepository.findByPostId(postId);
            List<Long> hashTags = new ArrayList<>();
            for (PostHashTag hashTag : hashTagList){
                hashTags.add(hashTag.getHashTagId());
            }
            myWishParams2.setHashTags(hashTags);

            result.add(myWishParams2);
        }
        return result;
    }

    public List<MyWishParams3> getMyWish(Long userId) {
        List<MyWishParams3> result = new ArrayList<>();
//        List<MyWish> list = myWishRepository.findAllOrderByWishTime();
//        int len = list.size();
//        if (len > 3){
//            for (int i=len-1; i>len-4;i++){
//                MyWishParams myWishParams = new MyWishParams();
//                myWishParams.setTitle(list.get(i).get);
//            }
//        } else {
//            for (MyWish myWish : list){
//
//            }
//        }
        return result;
    }


}
