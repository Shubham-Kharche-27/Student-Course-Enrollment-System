package com.shubham.Student.Course.Enrollment.System.Advice;

import com.shubham.Student.Course.Enrollment.System.Exception.CourseNotFoundException;
import com.shubham.Student.Course.Enrollment.System.Exception.EnrollmentNotFoundException;
import com.shubham.Student.Course.Enrollment.System.Exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleStudentNotFoundException(StudentNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<String> handleCourseNotFoundException(CourseNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(EnrollmentNotFoundException.class)
    public ResponseEntity<String> handleEnrollmentNotFoundException(EnrollmentNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NO_CONTENT);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGlobalException(Exception ex){
//        return new ResponseEntity<>("Internal Server Error!",HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
