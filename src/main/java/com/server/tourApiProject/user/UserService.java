package com.server.tourApiProject.user;

import com.server.tourApiProject.bigPost.post.Post;
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

    public UserParams2 getUser2(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
        UserParams2 userParams2 = new UserParams2();
        userParams2.setNickName(user.getNickName());
        userParams2.setProfileImage(user.getProfileImage());

        return userParams2;
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
        user.setKakao(false);

        userRepository.save(user);
    }

    public void createKakaoUser(KakaoUserParams userParam){
        User user = new User();
        user.setEmail(userParam.getEmail());
        user.setNickName(userParam.getNickName());
        user.setProfileImage(userParam.getProfileImage());
        user.setSignUpDt(LocalDateTime.now());
        user.setKakao(true);
        if(userParam.getMobilePhoneNumber()!=null)
            user.setMobilePhoneNumber(userParam.getMobilePhoneNumber());
        if(userParam.getSex()!=null)
            user.setSex(userParam.getSex());
        if(userParam.getBirthDay()!=null)
            user.setBirthDay(userParam.getBirthDay());
        if(userParam.getAgeRange()!=null)
            user.setAgeRange(userParam.getAgeRange());

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

    public Boolean checkDuplicateNickName(String nickName) {
        User user = userRepository.findByNickName(nickName);
        return user == null;
    }

    public Long logIn(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return -1L;
        }
        if (user.getPassword().equals(password)) {
            return user.getUserId();
        }
        return -1L;
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

    public void changeNickName(Long userId, String nickName) {
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
        user.setNickName(nickName);
        userRepository.save(user);
    }

    public void changeProfileImage(Long userId, String profileImageName) {
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
        user.setProfileImage(profileImageName);
        userRepository.save(user);
    }

    public Boolean changePassword(Long userId, String originPwd, String newPwd) {
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
        if (!user.getPassword().equals(originPwd)){
            return false;
        }
        user.setPassword(newPwd);
        userRepository.save(user);
        return true;
    }

    public List<Post> getMyPosts(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
        List<Post> myPosts = user.getMyPosts();
        return myPosts;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void deleteUser2(String email) {
        User user = userRepository.findByEmail(email);
        userRepository.delete(user);
    }

    public boolean checkIsKakao(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
        return user.getKakao();
    }
}
