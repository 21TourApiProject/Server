package com.server.tourApiProject.post;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository <Post, Long> {
    Post findByUserId(@Param("userId")Long userId);

}
