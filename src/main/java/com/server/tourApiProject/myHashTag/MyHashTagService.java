package com.server.tourApiProject.myHashTag;

import com.server.tourApiProject.hashTag.HashTag;
import com.server.tourApiProject.hashTag.HashTagRepository;
import com.server.tourApiProject.user.User;
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
public class MyHashTagService {
    private final MyHashTagRepository myHashTagRepository;
    private final UserRepository userRepository;
    private final HashTagRepository hashTagRepository;

    public List<String> getMyHashTag(Long userId) {
        List<String> myHashTagNameList = new ArrayList<>();
        List<MyHashTag> myHashTagList = myHashTagRepository.findByUserId(userId);
        for(MyHashTag p : myHashTagList) {
            myHashTagNameList.add(p.getHashTagName());
        }
        return myHashTagNameList;
    }

    public Long createMyHashTags(String mobilePhoneNumber, List<MyHashTagParams> myHashTagParams) {
        User user = userRepository.findByMobilePhoneNumber(mobilePhoneNumber);
        Long userId = user.getUserId();

        for(MyHashTagParams p : myHashTagParams) {
            MyHashTag myHashTag = new MyHashTag();
            myHashTag.setHashTagName(p.getHashTagName());
            myHashTag.setUser(user);
            myHashTag.setUserId(userId);
            HashTag hashTag = hashTagRepository.findByHashTagName(p.getHashTagName());
            myHashTag.setHashTagId(hashTag.getHashTagId());

            myHashTagRepository.save(myHashTag);
        }
        return userId;
    }
}
