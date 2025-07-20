package com.shubham.Student.Course.Enrollment.System.Repository;

import com.shubham.Student.Course.Enrollment.System.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course,Integer> {
}
