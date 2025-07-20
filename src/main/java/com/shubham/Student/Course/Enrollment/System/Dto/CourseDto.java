package com.shubham.Student.Course.Enrollment.System.Dto;
import java.util.ArrayList;
import java.util.List;


public class CourseDto {
    private int courseId;
    private String courseTitle;
    private String courseDescription;
    private String courseDuration;
    private List<EnrollmentDto> list = new ArrayList<>();

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

    public List<EnrollmentDto> getList() {
        return list;
    }

    public void setList(List<EnrollmentDto> list) {
        this.list = list;
    }
}
