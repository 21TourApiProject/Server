package com.server.tourApiProject.bigPost.postImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostImageRepository extends JpaRepository <PostImage,Long>{
    List<PostImage> findByPostId(@Param("postId") Long postId);
    PostImage findByPostImageListId(@Param("postImageListId")Long postImageListId);
    List<PostImage>findByPostObservePointId(@Param("postObservePointId")Long postObservePointId);
}
