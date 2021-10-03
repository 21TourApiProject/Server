package com.server.tourApiProject.user;

import com.server.tourApiProject.bigPost.post.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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

        boolean isDuplicate = true;
        while(isDuplicate){
            String nickname = randomNickName();
            if(userRepository.findByNickName(nickname) == null){
                isDuplicate = false;
                user.setNickName(nickname);
            }
        }
        user.setIsMarketing(userParam.getIsMarketing());
        user.setKakao(userParam.getKakao());
        user.setSignUpDt(LocalDateTime.now());

        userRepository.save(user);
    }

    private String randomNickName() {
        String[] front = {"별헤는","별난","별이좋은","별을찾는","별보는","반짝이는","비몽사몽","여행하는","우주속의","졸린","별그리는","반짝반짝","하품하는","코고는",
                "여행중인","별에서온","밤하늘의","여름밤의","겨울밤의","별빛속의","나른한","별똥별과","꾸벅꾸벅","야행성","배낭을멘","달빛속의","새벽감성","은하수속",
                "자유로운","캠핑하는","낭만적인","느낌있는","은하수와","옥탑방","꿈속의","잠든","감성적인","잠오는","설레는","행복한","로맨틱한","감미로운","신비로운","꿈꾸는","새벽녘의"};
        String[] back = {"너구리","뱁새","호랑이","햄스터","쿼카","미어캣","반달곰","칡","고영이","타조","낙타","라쿤","북극곰","막대사탕","보드카","위스키","막걸리",
                "영혼","꼬마유령","대학원생","돌하르방","마법사","하모니카","도깨비","반딧불이","멍뭉이","호롱불","사막여우","고슴도치","다람쥐","수달","천문학자","별사탕",
                "모닥불","히치하이커","벽난로","기타리스트","여행작가","몽상가","음유시인","고래","올빼미"};
        Random random = new Random();
        int f = random.nextInt(45);
        int b = random.nextInt(42);
        int n = random.nextInt(1000);
        return front[f] + " " + back[b] + n;
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
        if (user.getKakao()){
            return -2L;
        }
        if (user.getPassword().equals(password)) {
            return user.getUserId();
        }
        return -1L;
    }

    public Long kakaoLogIn(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return -1L;
        }
        return user.getUserId();
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
