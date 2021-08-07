package com.server.tourApiProject.post;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository <Post, Long> {
    Post findByUserId(@Param("userId")Long userId);

    List<Post> findUserById(@Param("userId") Long userId);
}
