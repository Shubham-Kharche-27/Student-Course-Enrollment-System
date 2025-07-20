package com.shubham.Student.Course.Enrollment.System.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrollmentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId")
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId")
    private Course course;
    private LocalDate enrollmentDate;
    private String enrollmentStatus;
    private String enrollmentGrade;

    public Enrollment(int enrollmentId, Student student, Course course, LocalDate enrollmentDate, String enrollmentStatus, String enrollmentGrade) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.enrollmentStatus = enrollmentStatus;
        this.enrollmentGrade = enrollmentGrade;
    }

    public Enrollment() {
    }

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
