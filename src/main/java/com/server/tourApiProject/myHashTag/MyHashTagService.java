package com.server.tourApiProject.myHashTag;

import com.server.tourApiProject.hashTag.HashTag;
import com.server.tourApiProject.hashTag.HashTagRepository;
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

    public void createMyHashTag(MyHashTagParams myHashTagParam) {
        MyHashTag myHashTag = new MyHashTag();
        myHashTag.setHashTagName(myHashTagParam.getHashTagName());

        HashTag hashTag = hashTagRepository.findByHashTagName(myHashTagParam.getHashTagName());
        myHashTag.setHashTagId(hashTag.getHashTagId());

        myHashTag.setUserId(myHashTagParam.getUserId());
        myHashTag.setUser(userRepository.findById(myHashTagParam.getUserId()).orElseThrow(IllegalAccessError::new));

        myHashTagRepository.save(myHashTag);
    }

    public List<MyHashTag> getMyHashTag(Long userId) {
        return myHashTagRepository.findByUserId(userId);
    }
}
