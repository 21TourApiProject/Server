package com.server.tourApiProject.bigPost.post;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository <Post, Long> {
    List<Post> findByUserId(@Param("userId") Long userId);
    List<Post> findByObservationId(@Param("ObservationId")Long observationId);
    List<Post> findByPostTitleAndPostContent( @Param("postTitle") String postTitle,@Param("postContent") String postContent);
    List<Post> findByAreaCode(@Param("areaCode") Long areaCode);
}
