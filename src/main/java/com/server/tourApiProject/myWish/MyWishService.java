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
import com.server.tourApiProject.touristPoint.contentType.ContentType;
import com.server.tourApiProject.touristPoint.contentType.ContentTypeRepository;
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
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MyWishService {

    private final MyWishRepository myWishRepository;
    private final ContentTypeRepository contentTypeRepository;
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

    //이전에 이미 찜을 해놓았는지 확인하기
    public Boolean isThereMyWish(Long userId, Long itemId, Integer wishType) {
        Optional<MyWish> myWish = myWishRepository.findByUserIdAndItemIdAndWishType(userId, itemId, wishType);
        return myWish.isPresent();
    }

    //찜해 놓은거 삭제
    public void deleteMyWish(Long userId, Long itemId, Integer wishType) {
        Optional<MyWish> myWishOp = myWishRepository.findByUserIdAndItemIdAndWishType(userId, itemId, wishType);
        MyWish myWish = myWishOp.get();
        myWishRepository.delete(myWish);
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
            if (!imageList.isEmpty()){
                ObserveImage observeImage = imageList.get(0);
                myWishParams01.setThumbnail(observeImage.getImage());
            } else {
                myWishParams01.setThumbnail(null);
            }

            myWishParams01.setTitle(observation.getObservationName());
            myWishParams01.setAddress(observation.getAddress());
            myWishParams01.setCat3Name(observation.getObserveType());
            myWishParams01.setOverviewSim(observation.getOutline().substring(0,15) + "...");

            List<ObserveHashTag> hashTagList = observeHashTagRepository.findByObservationId(observationId);
            List<String> hashTagNames = new ArrayList<>();
            int i = 0;
            for (ObserveHashTag hashTag : hashTagList){
                if (i > 2)
                    break;
                hashTagNames.add(hashTag.getHashTagName());
                i++;
            }
            myWishParams01.setHashTagNames(hashTagNames);

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
            myWishParams01.setAddress(touristData.getAddr());
            myWishParams01.setCat3Name(contentTypeRepository.findByCat3Code(touristData.getCat3()).getCat3Name());
            myWishParams01.setOverviewSim(touristData.getOverviewSim());

            List<TouristDataHashTag> hashTagList = touristDataHashTagRepository.findByContentId(contentId);
            List<String> hashTagNames = new ArrayList<>();
            int i = 0;
            for (TouristDataHashTag hashTag : hashTagList){
                if(i > 2)
                    break;
                hashTagNames.add(hashTag.getHashTagName());
                i++;
            }
            myWishParams01.setHashTagNames(hashTagNames);

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
            if (!imageList.isEmpty()){
                PostImage postImage = imageList.get(0);
                myWishParams2.setThumbnail(postImage.getImageName());
            } else {
                myWishParams2.setThumbnail(null);
            }

            myWishParams2.setTitle(post.getPostTitle());

            myWishParams2.setNickName(post.getUser().getNickName());
            myWishParams2.setProfileImage(post.getUser().getProfileImage());

            List<PostHashTag> hashTagList = postHashTagRepository.findByPostId(postId);
            int i = 0;
            List<String> hashTagNames = new ArrayList<>();
            for (PostHashTag hashTag : hashTagList){
                if(i > 2)
                    break;
                hashTagNames.add(hashTag.getHashTagName());
                i++;
            }
            myWishParams2.setHashTagNames(hashTagNames);

            result.add(myWishParams2);
        }
        return result;
    }

    public List<MyWishParams3> getMyWish3(Long userId) {
        List<MyWishParams3> result = new ArrayList<>();
        List<MyWish> list = myWishRepository.findByUserId(userId);
        List<MyWish> three = new ArrayList<>();
        int len = list.size();
        if (len > 3){
            for (int i=len-1; i>len-4; i--){
                three.add(list.get(i));
            }
        } else {
            three = list;
        }

        for (MyWish myWish : three){
            MyWishParams3 myWishParams3 = new MyWishParams3();
            if (myWish.getWishType() == 0){
                myWishParams3.setWishType(0);
                Observation observation = observationRepository.findById(myWish.getItemId()).orElseThrow(IllegalAccessError::new);
                myWishParams3.setTitle(observation.getObservationName());
                List<ObserveImage> imageList = observeImageRepository.findByObservationId(myWish.getItemId());
                if (!imageList.isEmpty()) {
                    ObserveImage observeImage = imageList.get(0);
                    myWishParams3.setThumbnail(observeImage.getImage());
                } else{
                    myWishParams3.setThumbnail(null);
                }

                result.add(myWishParams3);
            }
            else if (myWish.getWishType() == 1){
                myWishParams3.setWishType(1);
                TouristData touristData = touristDataRepository.findById(myWish.getItemId()).orElseThrow(IllegalAccessError::new);
                myWishParams3.setTitle(touristData.getTitle());
                myWishParams3.setThumbnail(touristData.getFirstImage());

                result.add(myWishParams3);
            }
            else if (myWish.getWishType() == 2){
                myWishParams3.setWishType(2);
                Post post = postRepository.findById(myWish.getItemId()).orElseThrow(IllegalAccessError::new);
                myWishParams3.setTitle(post.getPostTitle());
                List<PostImage> imageList = postImageRepository.findByPostId(myWish.getItemId());
                if (!imageList.isEmpty()) {
                    PostImage postImage = imageList.get(0);
                    myWishParams3.setThumbnail(postImage.getImageName());
                } else{
                    myWishParams3.setThumbnail(null);
                }

                result.add(myWishParams3);
            }

        }
        return result;
    }

}
