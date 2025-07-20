package com.shubham.Student.Course.Enrollment.System.Service;

import com.shubham.Student.Course.Enrollment.System.Dto.CourseDto;
import com.shubham.Student.Course.Enrollment.System.Entity.Course;
import com.shubham.Student.Course.Enrollment.System.Exception.CourseNotFoundException;
import com.shubham.Student.Course.Enrollment.System.Repository.CourseRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<Page<CourseDto>> getAllCourseData(int pageNum,int pageSize,String sortBy){
        Pageable pageable = PageRequest.of(pageNum,pageSize, Sort.by(sortBy));
        Page<Course> coursePage = courseRepo.findAll(pageable);
        Page<CourseDto> courseDtoPage = coursePage.map(Course->modelMapper.map(Course,CourseDto.class));
        return new ResponseEntity<>(courseDtoPage, HttpStatus.OK);
    }

    public ResponseEntity<CourseDto> getCourseDataById(int courseId){
        Course course = courseRepo.findById(courseId).orElseThrow(()-> new CourseNotFoundException("Course not found!"));
        return new ResponseEntity<>(modelMapper.map(course,CourseDto.class),HttpStatus.OK);
    }

    public ResponseEntity<String> createCourseData(CourseDto courseDto){
        if(courseDto!=null){
            Course course = modelMapper.map(courseDto,Course.class);
            courseRepo.save(course);
            return new ResponseEntity<>("Course data created successfully!",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Course data not created!",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateCourseData(int courseId,CourseDto courseDto){
        Course course = courseRepo.findById(courseId).orElseThrow(()->new CourseNotFoundException("Course not found!"));
        if(courseDto.getCourseTitle()!=null){
            course.setCourseTitle(courseDto.getCourseTitle());
        }
        if(courseDto.getCourseDescription()!=null){
            course.setCourseDescription(courseDto.getCourseDescription());
        }
        if(courseDto.getCourseDuration()!=null){
            course.setCourseDuration(courseDto.getCourseDuration());
        }
        courseRepo.save(course);
        return new ResponseEntity<>("Course data updated successfully!",HttpStatus.OK);
    }

    public ResponseEntity<String> deleteCourseData(int courseId){
        Course course = courseRepo.findById(courseId).orElseThrow(()->new CourseNotFoundException("Course not found!"));
        courseRepo.deleteById(courseId);
        return new ResponseEntity<>("Course data deleted successfully!",HttpStatus.NO_CONTENT);
    }
}
