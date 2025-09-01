package com.shubham.Student.Course.Enrollment.System.Repository;

import com.shubham.Student.Course.Enrollment.System.Entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment,Integer> {
    List<Enrollment> findTop4ByOrderByEnrollmentDateDesc();
}
