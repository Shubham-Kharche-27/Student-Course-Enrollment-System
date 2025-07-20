package com.shubham.Student.Course.Enrollment.System.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    private String courseTitle;
    private String courseDescription;
    private String courseDuration;
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference(value = "courseEnrollment")
    private List<Enrollment> courseList = new ArrayList<>();

    public Course(int courseId, String courseTitle, String courseDescription, String courseDuration, List<Enrollment> list) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.courseDuration = courseDuration;
        this.courseList = list;
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

    public List<Enrollment> getList() {
        return courseList;
    }

    public void setList(List<Enrollment> list) {
        this.courseList = list;
    }
}
