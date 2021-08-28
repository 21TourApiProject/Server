package com.server.tourApiProject.myWish;

import com.server.tourApiProject.bigPost.post.PostRepository;
import com.server.tourApiProject.touristPoint.touristData.TouristData;
import com.server.tourApiProject.touristPoint.touristData.TouristDataRepository;
import com.server.tourApiProject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
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
                myWish.setWishTime(LocalTime.now()); //필수
                break;
            case 1: //관광지
                touristDataRepository.findById(itemId).orElseThrow(IllegalAccessError::new);  //itemId에 해당하는 관광지가 없으면 오류 발생
                myWish.setUserId(userId);
                myWish.setUser(userRepository.findById(userId).orElseThrow(IllegalAccessError::new));
                myWish.setWishType(1);
                myWish.setItemId(itemId);
                myWish.setWishTime(LocalTime.now()); //필수
                myWishRepository.save(myWish);
                break;
            case 2: //게시물
                postRepository.findById(itemId).orElseThrow(IllegalAccessError::new);  //itemId에 해당하는 게시물이 없으면 오류 발생
                myWish.setUserId(userId);
                myWish.setUser(userRepository.findById(userId).orElseThrow(IllegalAccessError::new));
                myWish.setWishType(2);
                myWish.setItemId(itemId);
                myWish.setWishTime(LocalTime.now()); //필수
                myWishRepository.save(myWish);
                break;
        }
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
            //myWishParams1.setHashTags(); 나중에 수정
            result.add(myWishParams01);
        }
        return result;
    }

    public List<MyWishParams> getMyWish(Long userId) {
        List<MyWishParams> result = new ArrayList<>();
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
