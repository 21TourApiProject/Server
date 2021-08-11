package com.server.tourApiProject.image;

import com.server.tourApiProject.hashTag.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ImageRepository extends JpaRepository<Image,Long> {
    Image findByImageName(@Param("imageName") String imageName);
}
