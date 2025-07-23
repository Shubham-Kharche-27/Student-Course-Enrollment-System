package com.shubham.Student.Course.Enrollment.System.Controller;

import com.shubham.Student.Course.Enrollment.System.Dto.CourseDto;
import com.shubham.Student.Course.Enrollment.System.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity<Page<CourseDto>> getAllCourses(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "3")int pageSize,
            @RequestParam(defaultValue = "courseId")String sortBy
    ){
        return new ResponseEntity<>(courseService.getAllCourseData(pageNum-1,pageSize,sortBy), HttpStatus.OK);
    }

    @GetMapping("/get/byId/{courseId}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable int courseId){
        return new ResponseEntity<>(courseService.getCourseDataById(courseId),HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto){
        return new ResponseEntity<>(courseService.createCourseData(courseDto),HttpStatus.CREATED);
    }

    @PutMapping("/put/{courseId}")
    public ResponseEntity<String> updateCourse(@PathVariable int courseId,@RequestBody CourseDto courseDto){
        return new ResponseEntity<>(courseService.updateCourseData(courseId,courseDto),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable int courseId){
        return new ResponseEntity<>(courseService.deleteCourseData(courseId),HttpStatus.NO_CONTENT);
    }
}
