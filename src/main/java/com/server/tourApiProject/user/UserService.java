package com.server.tourApiProject.user;

import com.server.tourApiProject.myHashTag.MyHashTag;
import com.server.tourApiProject.myHashTag.MyHashTagRepository;
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



    public void createUser(UserParams userParam){
        User user = new User();
        user.setRealName(userParam.getRealName());
        user.setSex(userParam.getSex());
        user.setBirthDay(userParam.getBirthDay());
        user.setMobilePhoneNumber(userParam.getMobilePhoneNumber());
        user.setEmail(userParam.getEmail());
        user.setPassword(userParam.getPassword());
        user.setNickName(userParam.getEmail());
        user.setSignUpDt(LocalDateTime.now());

        userRepository.save(user);
    }

    public User updateUser(Long userId, UserParams userParam) {
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
//        if (!userParam.getEmail().isEmpty())
//            user.setEmail(userParam.getEmail());
//        쓸일이 있을지 모르겠음

        return userRepository.save(user);
    }

    public Boolean checkDuplicateEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user == null;
    }

    public Boolean checkDuplicateMobilePhoneNumber(String mobilePhoneNumber) {
        User user = userRepository.findByMobilePhoneNumber(mobilePhoneNumber);
        return user == null;
    }

    public Boolean logIn(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null){
            return false;
        }
        return user.getPassword().equals(password);
    }

    public String getEmail(String realName, String mobilePhoneNumber) {
        User user = userRepository.findByMobilePhoneNumber(mobilePhoneNumber);
        if (user == null){
            return "none";
        }
        if (user.getRealName().equals(realName)){
            return user.getEmail();
        }return "none";
    }

    public String getPassword(String email, String realName, String mobilePhoneNumber) {
        User user = userRepository.findByEmail(email);
        if (user == null){
            return "none";
        }
        if (user.getRealName().equals(realName) && user.getMobilePhoneNumber().equals(mobilePhoneNumber)){
            return user.getPassword();
        }
        return "none";
    }
}
