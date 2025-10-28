package com.shubham.Student.Course.Enrollment.System.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shubham.Student.Course.Enrollment.System.Entity.Enums.EnrollmentGrade;
import com.shubham.Student.Course.Enrollment.System.Entity.Enums.EnrollmentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

public class EnrollmentDto {

    private int enrollmentId;
    private int studentId;
    private int courseId;
    private String studentName;
    private String studentEmail;
    private String courseName;
    private String courseCode;
    private String courseInstructor;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate enrollmentDate;
    private EnrollmentStatus enrollmentStatus;

    @Enumerated(EnumType.STRING)
    private EnrollmentGrade enrollmentGrade = EnrollmentGrade.Not_Graded;

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(String courseInstructor) {
        this.courseInstructor = courseInstructor;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public EnrollmentStatus getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public EnrollmentGrade getEnrollmentGrade() {
        return enrollmentGrade;
    }

    public void setEnrollmentGrade(EnrollmentGrade enrollmentGrade) {
        this.enrollmentGrade = enrollmentGrade;
    }
}
