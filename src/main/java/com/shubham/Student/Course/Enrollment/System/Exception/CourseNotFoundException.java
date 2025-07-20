package com.shubham.Student.Course.Enrollment.System.Exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String message) {
        super(message);
    }
}
