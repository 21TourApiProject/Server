package com.server.tourApiProject.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    // 중복 아이디가 있는지 조회
    User findByLoginId(@Param("loginId") String loginId);

}
