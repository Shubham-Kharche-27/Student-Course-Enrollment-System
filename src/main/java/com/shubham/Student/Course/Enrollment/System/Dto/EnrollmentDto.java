package com.shubham.Student.Course.Enrollment.System.Dto;

import java.time.LocalDate;


public class EnrollmentDto {

    private int enrollmentId;
    private StudentDto student;
    private CourseDto course;
    private LocalDate enrollmentDate;
    private String enrollmentStatus;
    private String enrollmentGrade;

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public StudentDto getStudent() {
        return student;
    }

    public void setStudent(StudentDto student) {
        this.student = student;
    }

    public CourseDto getCourse() {
        return course;
    }

    public void setCourse(CourseDto course) {
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
