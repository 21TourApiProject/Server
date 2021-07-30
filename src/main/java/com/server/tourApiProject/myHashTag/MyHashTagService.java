package com.server.tourApiProject.myHashTag;

import com.server.tourApiProject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MyHashTagService {
    private final MyHashTagRepository myHashTagRepository;
    private final UserRepository userRepository;

    public void createMyHashTag(MyHashTagParams myHashTagParam) {
        MyHashTag myHashTag = new MyHashTag();
        myHashTag.setHashTagId(myHashTagParam.getHashTagId());
        myHashTag.setUserId(myHashTagParam.getUserId());
        myHashTag.setUser(userRepository.findById(myHashTagParam.getUserId()).orElseThrow(IllegalAccessError::new));

        myHashTagRepository.save(myHashTag);
    }
}
