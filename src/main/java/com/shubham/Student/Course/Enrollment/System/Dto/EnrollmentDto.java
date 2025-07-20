package com.shubham.Student.Course.Enrollment.System.Dto;

import com.shubham.Student.Course.Enrollment.System.Entity.Course;
import com.shubham.Student.Course.Enrollment.System.Entity.Student;
import java.time.LocalDate;


public class EnrollmentDto {

    private int enrollmentId;
    private Student student;
    private Course course;
    private LocalDate enrollmentDate;
    private String enrollmentStatus;
    private String enrollmentGrade;

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(String enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public String getEnrollmentGrade() {
        return enrollmentGrade;
    }

    public void setEnrollmentGrade(String enrollmentGrade) {
        this.enrollmentGrade = enrollmentGrade;
    }
}
