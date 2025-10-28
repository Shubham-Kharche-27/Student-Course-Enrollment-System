package com.shubham.Student.Course.Enrollment.System.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shubham.Student.Course.Enrollment.System.Entity.Enums.EnrollmentGrade;
import com.shubham.Student.Course.Enrollment.System.Entity.Enums.EnrollmentStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrollmentId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentId")
    @JsonBackReference(value = "studentEnrollment")
    private Student student;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courseId")
    @JsonBackReference(value = "courseEnrollment")
    private Course course;
    @Column(columnDefinition = "DATE")
    private LocalDate enrollmentDate;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus enrollmentStatus;
    private EnrollmentGrade enrollmentGrade;

    @PrePersist
    public void setEnrollmentDate(){
        enrollmentDate = LocalDate.now();
    }

    public Enrollment(int enrollmentId, Student student, Course course, LocalDate enrollmentDate, EnrollmentStatus enrollmentStatus, EnrollmentGrade enrollmentGrade) {
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
