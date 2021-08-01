package com.server.tourApiProject.postHashTag;

import com.server.tourApiProject.myHashTag.MyHashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostHashTagRepository extends JpaRepository<PostHashTag, Long> {
    List<PostHashTag> findByPostId(@Param("postId") Long postId);
}
