package com.server.tourApiProject.myHashTag;

import com.server.tourApiProject.hashTag.HashTag;
import com.server.tourApiProject.hashTag.HashTagRepository;
import com.server.tourApiProject.user.User;
import com.server.tourApiProject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MyHashTagService {
    private final MyHashTagRepository myHashTagRepository;
    private final UserRepository userRepository;
    private final HashTagRepository hashTagRepository;


    public List<MyHashTag> getMyHashTag(Long userId) {
        return myHashTagRepository.findByUserId(userId);
    }

    public void createMyHashTags(List<MyHashTagParams> myHashTagParams) {
        for(MyHashTagParams p : myHashTagParams){
            MyHashTag myHashTag = new MyHashTag();
            myHashTag.setHashTagName(p.getHashTagName());

            HashTag hashTag = hashTagRepository.findByHashTagName(p.getHashTagName());
            myHashTag.setHashTagId(hashTag.getHashTagId());

            User user = userRepository.findByMobilePhoneNumber(p.getMobilePhoneNumber());
            myHashTag.setUser(user);
            myHashTag.setUserId(user.getUserId());

            myHashTagRepository.save(myHashTag);
        }
    }
}
