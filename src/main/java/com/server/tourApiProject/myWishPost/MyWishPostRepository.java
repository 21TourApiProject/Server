package com.server.tourApiProject.myWishPost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MyWishPostRepository  extends JpaRepository<MyWishPost, Long> {

    List<MyWishPost> findByUserId(@Param("userId") Long userId);
}
