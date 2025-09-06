package com.shubham.Student.Course.Enrollment.System.Repository;

import com.shubham.Student.Course.Enrollment.System.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {
    List<Student> findByStudentNameContainingIgnoreCase(String studentName);
}
