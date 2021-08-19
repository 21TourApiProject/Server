package com.server.tourApiProject.constellation;

import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalDateKeyDeserializer;
import com.server.tourApiProject.bigPost.post.Post;
import com.server.tourApiProject.myWishPost.MyWishPost;
import com.server.tourApiProject.myWishPost.MyWishPostParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ConstellationService {
    private final ConstellationRepository constellationRepository;

    public List<Constellation> getAllConstellation() {
        return constellationRepository.findAll();
    }

    public void createConstellation(Constellation constellation) {
        constellationRepository.save(constellation);
    }

    public List<ConstellationParams> getTodayConst() {
        List<ConstellationParams> result= new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        List<Constellation> list = constellationRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(currentDate, currentDate);

        for(Constellation cl : list){
            Constellation constellation = constellationRepository.findById(cl.getConstId()).orElseThrow(IllegalAccessError::new);

            ConstellationParams params = new ConstellationParams();
            params.setConstId(constellation.getConstId());
            params.setConstImage(constellation.getConstImage());
            params.setConstName(constellation.getConstName());
            result.add(params);
        }
        return result;
    }
}

//    public List<MyWishPostParams> getMyWishPosts(Long userId) {
//        List<MyWishPostParams> result= new ArrayList<>();
//
//        List<MyWishPost> list = myWishPostRepository.findByUserId(userId);
//        for (MyWishPost wp : list){
//            Post post = postRepository.findById(wp.getPostId()).orElseThrow(IllegalAccessError::new);
//
//            MyWishPostParams params = new MyWishPostParams();
//            params.setPostId(post.getPostId());
//            params.setThumbnail(""); //추후 첫번째 이미지로 수정
//            params.setTitle(post.getPostContent()); //추후 제목으로 수정
//            result.add(params);
//        }
//        return result;
//    }
