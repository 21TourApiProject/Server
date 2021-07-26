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



    public User createUser(UserParams userParam){
        User user = new User();
        user.setRealName(userParam.getRealName());
        user.setSex(userParam.getSex());
        user.setBirthDay(userParam.getBirthDay());
        user.setMobilePhoneNumber(userParam.getMobilePhoneNumber());
        user.setEmail(userParam.getEmail());
        user.setLoginId(userParam.getLoginId());
        user.setPassword(userParam.getPassword());
        user.setNickName(userParam.getLoginId());
        //user.setUserHashTags(userParam.getUserHashTags());

        user.setSignUpDt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User updateUser(Long userId, UserParams userParam) {
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
//        if (!userParam.getEmail().isEmpty())
//            user.setEmail(userParam.getEmail());
//        쓸일이 있을지 모르겠음

        return userRepository.save(user);
    }

    public Boolean checkDuplicateLoginId(String loginId) {
        User user = userRepository.findByLoginId(loginId);
        return user == null;
    }
}
