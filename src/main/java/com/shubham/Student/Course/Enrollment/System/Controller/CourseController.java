package com.shubham.Student.Course.Enrollment.System.Controller;

import com.shubham.Student.Course.Enrollment.System.Dto.CourseDto;
import com.shubham.Student.Course.Enrollment.System.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        return courseService.getAllCourseData(pageNum-1,pageSize,sortBy);
    }

    @GetMapping("/get/byId/{courseId}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable int courseId){
        return courseService.getCourseDataById(courseId);
    }

    @PostMapping("/post")
    public ResponseEntity<String> createCourse(@RequestBody CourseDto courseDto){
        return courseService.createCourseData(courseDto);
    }

    @PutMapping("/put/{courseId}")
    public ResponseEntity<String> updateCourse(@PathVariable int courseId,@RequestBody CourseDto courseDto){
        return courseService.updateCourseData(courseId,courseDto);
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable int courseId){
        return courseService.deleteCourseData(courseId);
    }
}
