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
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MyHashTagService {
    private final MyHashTagRepository myHashTagRepository;
    private final UserRepository userRepository;
    private final HashTagRepository hashTagRepository;

    List<Long> getMyHashTagIdList(Long userId){
        List<Long> myHashTagIdList = new ArrayList<>();
        List<MyHashTag> myHashTagList = myHashTagRepository.findByUserId(userId);
        for (MyHashTag p: myHashTagList){
            myHashTagIdList.add(p.getHashTagId());
        }return  myHashTagIdList;
    }

    public List<String> getMyHashTag(Long userId) {
        List<String> myHashTagNameList = new ArrayList<>();
        List<MyHashTag> myHashTagList = myHashTagRepository.findByUserId(userId);
        for(MyHashTag p : myHashTagList) {
            myHashTagNameList.add(p.getHashTagName());
        }
        return myHashTagNameList;
    }

    public Long createMyHashTags(String email, List<MyHashTagParams> myHashTagParams) {
        User user = userRepository.findByEmail(email);
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

    public void changeMyHashTag(Long userId, List<MyHashTagParams> myHashTagParams) {
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
        List<MyHashTag> origin = myHashTagRepository.findByUserId(userId);
        myHashTagRepository.deleteAll(origin);
        for(MyHashTagParams p : myHashTagParams) {
            MyHashTag myHashTag = new MyHashTag();
            myHashTag.setHashTagName(p.getHashTagName());
            myHashTag.setUser(user);
            myHashTag.setUserId(userId);
            HashTag hashTag = hashTagRepository.findByHashTagName(p.getHashTagName());
            myHashTag.setHashTagId(hashTag.getHashTagId());
            myHashTagRepository.save(myHashTag);
        }
    }

    public List<String> getMyHashTag3(Long userId) {
        List<String> myHashTagNameList = new ArrayList<>();
        List<MyHashTag> myHashTagList = myHashTagRepository.findByUserId(userId);
        int i = 0;
        for(MyHashTag p : myHashTagList) {
            if (i > 2)
                break;
            myHashTagNameList.add(p.getHashTagName());
            i++;
        }
        return myHashTagNameList;
    }
}
