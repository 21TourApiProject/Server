package com.server.tourApiProject.observation.ObserveImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObserveImageRepository extends JpaRepository <ObserveImage,Long>{
    List<ObserveImage> findByPostId(@Param("postId") Long postId);
    ObserveImage findByPostImageListId(@Param("postImageListId")Long postImageListId);
}
