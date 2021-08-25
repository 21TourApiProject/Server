package com.server.tourApiProject.myWish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MyWishRepository extends JpaRepository<MyWish, Long> {

    List<MyWish> findByUserIdAndWishType(@Param("userId") Long userId, @Param("wishType") Integer wishType);
}
