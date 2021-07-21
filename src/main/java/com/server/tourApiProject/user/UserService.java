package com.server.tourApiProject.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
        return user;
    }

    public User createUser(UserParam userParam){
        User user = new User();
        user.setUserId(userParam.getUserId());
        user.setEmail(userParam.getEmail());
        user.setMobilePhoneNumber(userParam.getMobilePhoneNumber());
        user.setNickName(userParam.getNickName());
        user.setSignUpDt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User updateUser(Long userId, UserParam userParam) {
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
        if (!userParam.getEmail().isEmpty())
            user.setEmail(userParam.getEmail());
        if (!userParam.getMobilePhoneNumber().isEmpty())
            user.setMobilePhoneNumber(userParam.getMobilePhoneNumber());
        if (!userParam.getNickName().isEmpty())
            user.setNickName(userParam.getNickName());
        return userRepository.save(user);
    }
}
