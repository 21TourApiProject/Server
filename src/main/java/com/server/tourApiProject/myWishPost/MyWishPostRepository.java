package com.server.tourApiProject.myWishPost;

import com.server.tourApiProject.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyWishPostRepository  extends JpaRepository<MyWishPost, Long> {
}
