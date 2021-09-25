package com.server.tourApiProject.observation.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByObservationId(@Param("observationId") Long observationId);
}
