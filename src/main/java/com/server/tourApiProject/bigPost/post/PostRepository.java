package com.server.tourApiProject.bigPost.post;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository <Post, Long> {
    List<Post> findByUserId(@Param("userId") Long userId);
    Post findByPostContent(@Param("postContent")String postContent);
}
