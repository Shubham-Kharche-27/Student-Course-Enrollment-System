package com.shubham.Student.Course.Enrollment.System.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    private String courseTitle;
    private String courseCode;
    private String courseDescription;
    private String courseInstructor;
    private Integer courseCredit;
    private Long coursePrice;
    private Long totalEnrollment;
    private LocalDate courseStartDate;
    private LocalDate courseEndDate;
    private String courseDuration;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "courseEnrollment")
    private List<Enrollment> courseList = new ArrayList<>();


    public Course(List<Enrollment> courseList, String courseDuration, LocalDate courseEndDate, LocalDate courseStartDate, Long totalEnrollment, Long coursePrice, Integer courseCredit, String courseInstructor, String courseDescription,String courseCode, String courseTitle, int courseId) {
        this.courseList = courseList;
        this.courseDuration = courseDuration;
        this.courseEndDate = courseEndDate;
        this.courseStartDate = courseStartDate;
        this.totalEnrollment = totalEnrollment;
        this.coursePrice = coursePrice;
        this.courseCredit = courseCredit;
        this.courseInstructor = courseInstructor;
        this.courseDescription = courseDescription;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.courseId = courseId;
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

    public Integer getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(Integer courseCredit) {
        this.courseCredit = courseCredit;
    }

    public Long getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(Long coursePrice) {
        this.coursePrice = coursePrice;
    }

    public Long getTotalEnrollment() {
        return totalEnrollment;
    }

    public void setTotalEnrollment(Long totalEnrollment) {
        this.totalEnrollment = totalEnrollment;
    }

    public LocalDate getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(LocalDate courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public LocalDate getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(LocalDate courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public Course() {
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public List<Enrollment> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Enrollment> courseList) {
        this.courseList = courseList;
    }
}
