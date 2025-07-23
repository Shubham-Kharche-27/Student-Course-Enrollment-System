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
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Page<CourseDto> getAllCourseData(int pageNum,int pageSize,String sortBy){
        Pageable pageable = PageRequest.of(pageNum,pageSize, Sort.by(sortBy));
        Page<Course> coursePage = courseRepo.findAll(pageable);
        return coursePage.map(Course->modelMapper.map(Course,CourseDto.class));
    }

    public CourseDto getCourseDataById(int courseId){
        Course course = courseRepo.findById(courseId).orElseThrow(()-> new CourseNotFoundException("Course not found!"));
        return modelMapper.map(course,CourseDto.class);
    }

    public CourseDto createCourseData(CourseDto courseDto){
            Course course = modelMapper.map(courseDto,Course.class);
            return modelMapper.map(courseRepo.save(course),CourseDto.class);
    }

    public String updateCourseData(int courseId,CourseDto courseDto){
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
        return "Course data updated successfully!";
    }

    public String deleteCourseData(int courseId){
        Course course = courseRepo.findById(courseId).orElseThrow(()->new CourseNotFoundException("Course not found!"));
        courseRepo.deleteById(courseId);
        return "Course data deleted successfully!";
    }
}
