package com.server.tourApiProject.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
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
        user.setNickName(userParam.getNickName());
        user.setSignUpDt(LocalDateTime.now());

        return userRepository.save(user);
    }
}
