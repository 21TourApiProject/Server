package com.server.tourApiProject.bigPost.postObservePoint;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostObservePointRepository extends JpaRepository<PostObservePoint, Long > {
    PostObservePoint findByObservePointName(@Param("observePointName")String observePointName);
}
