package com.server.tourApiProject.myWish;

import com.server.tourApiProject.bigPost.post.PostRepository;
import com.server.tourApiProject.touristPoint.touristData.TouristData;
import com.server.tourApiProject.touristPoint.touristData.TouristDataRepository;
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
public class MyWishService {
    private final UserRepository userRepository;
    private final MyWishRepository myWishRepository;
    private final PostRepository postRepository;
    private final TouristDataRepository touristDataRepository;

    public void createMyWish(Long userId, Long itemId, Integer wishType) {
        MyWish myWish = new MyWish();
        switch(wishType) {
            case 0: //관측지

                break;
            case 1: //관광지
                touristDataRepository.findById(itemId).orElseThrow(IllegalAccessError::new);  //itemId에 해당하는 관광지가 없으면 오류 발생
                myWish.setUserId(userId);
                myWish.setUser(userRepository.findById(userId).orElseThrow(IllegalAccessError::new));
                myWish.setWishType(1);
                myWish.setContentId(itemId);
                myWishRepository.save(myWish);
                break;
            case 2: //게시물
                postRepository.findById(itemId).orElseThrow(IllegalAccessError::new);  //itemId에 해당하는 게시물이 없으면 오류 발생
                myWish.setUserId(userId);
                myWish.setUser(userRepository.findById(userId).orElseThrow(IllegalAccessError::new));
                myWish.setWishType(2);
                myWish.setPostId(itemId);
                myWishRepository.save(myWish);
                break;
        }
    }

    //찜한 관광지 목록 불러오기
    public List<MyWishParams01> getMyWishTouristPoint(Long userId) {
        List<MyWishParams01> result= new ArrayList<>();
        List<MyWish> wish = myWishRepository.findByUserIdAndWishType(userId, 1);
        for (MyWish myWish: wish){
            Long contentId = myWish.getContentId();
            TouristData touristData = touristDataRepository.findById(contentId).orElseThrow(IllegalAccessError::new);
            MyWishParams01 myWishParams01 = new MyWishParams01();
            myWishParams01.setItemId(contentId);
            myWishParams01.setThumbnail(touristData.getFirstImage());
            myWishParams01.setTitle(touristData.getTitle());
            myWishParams01.setAddress(touristData.getAddr1());
            myWishParams01.setCat3(touristData.getCat3());
            myWishParams01.setOverviewSim(touristData.getOverviewSim());
            //myWishParams1.setHashTags(); 나중에 수정
            result.add(myWishParams01);
        }
        return result;
    }

}
