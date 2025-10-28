package com.shubham.Student.Course.Enrollment.System.Service;

import com.shubham.Student.Course.Enrollment.System.Dto.EnrollmentDto;
import com.shubham.Student.Course.Enrollment.System.Dto.StudentDto;
import com.shubham.Student.Course.Enrollment.System.Entity.Course;
import com.shubham.Student.Course.Enrollment.System.Entity.Enrollment;
import com.shubham.Student.Course.Enrollment.System.Entity.Student;
import com.shubham.Student.Course.Enrollment.System.Exception.CourseNotFoundException;
import com.shubham.Student.Course.Enrollment.System.Exception.EnrollmentNotFoundException;
import com.shubham.Student.Course.Enrollment.System.Exception.StudentNotFoundException;
import com.shubham.Student.Course.Enrollment.System.Repository.CourseRepo;
import com.shubham.Student.Course.Enrollment.System.Repository.EnrollmentRepo;
import com.shubham.Student.Course.Enrollment.System.Repository.StudentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepo enrollmentRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Page<EnrollmentDto> getAllEnrollmentData(int pageNum, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));
        Page<Enrollment> enrollmentPage = enrollmentRepo.findAll(pageable);
        return enrollmentPage.map(Enrollment -> modelMapper.map(Enrollment, EnrollmentDto.class));
    }

    public EnrollmentDto getEnrollmentDataById(int enrollmentId) {
        Enrollment enrollment = enrollmentRepo.findById(enrollmentId).orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found!"));
        return modelMapper.map(enrollment, EnrollmentDto.class);
    }

    public EnrollmentDto createEnrollmentData(EnrollmentDto enrollmentDto) {
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentStatus(enrollmentDto.getEnrollmentStatus());
        Student student = studentRepo.findById(enrollmentDto.getStudentId()).orElse(null);
        Course course = courseRepo.findById(enrollmentDto.getCourseId()).orElse(null);
        if (student == null || course == null) {
            throw new StudentNotFoundException("Student Or Course Not Found");
        }
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        student.getStudentList().add(enrollment);
        course.getCourseList().add(enrollment);
        return modelMapper.map(enrollmentRepo.save(enrollment), EnrollmentDto.class);
    }


    public String updateEnrollmentData(int enrollmentId, EnrollmentDto enrollmentDto) {
        Enrollment enrollment = enrollmentRepo.findById(enrollmentId).orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found!"));
//        if (enrollmentDto.getStudentId() != 0) {
//            int studentId = enrollmentDto.getStudentId();
//            Student studentEntity = studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student not found"));
//            enrollment.setStudent(studentEntity);
//            studentEntity.getStudentList().add(enrollment);
//        }
//        if (enrollmentDto.getCourseId() != 0) {
//            int courseId = enrollmentDto.getCourseId();
//            Course courseEntity = courseRepo.findById(courseId)
//                    .orElseThrow(() -> new CourseNotFoundException("Course not found"));
//            enrollment.setCourse(courseEntity);
//            courseEntity.getCourseList().add(enrollment);
//        }
        if (enrollmentDto.getEnrollmentStatus() != null) {
            enrollment.setEnrollmentStatus(enrollmentDto.getEnrollmentStatus());
        }
        if (enrollmentDto.getEnrollmentGrade() != null) {
            enrollment.setEnrollmentGrade(enrollmentDto.getEnrollmentGrade());
        }
        enrollmentRepo.save(enrollment);
        return "Enrollment data updated successfully!";
    }


    public String deleteEnrollmentData(int enrollmentId) {
        Enrollment enrollment = enrollmentRepo.findById(enrollmentId).orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found!"));
        enrollmentRepo.deleteById(enrollmentId);
        return "Enrollment data deleted successfully!";
    }

    public Long getCountOfEnrollment() {
        return enrollmentRepo.count();
    }

    public List<EnrollmentDto> getRecentEnrollmentData() {
        List<Enrollment> enrollments = enrollmentRepo.findTop4ByOrderByEnrollmentDateDesc();
        List<EnrollmentDto> dtos = new ArrayList<>();
        for(Enrollment enrollment:enrollments){
            EnrollmentDto dto = new EnrollmentDto();
            dto.setEnrollmentId(enrollment.getEnrollmentId());
            dto.setStudentId(enrollment.getStudent().getStudentId());
            dto.setStudentName(enrollment.getStudent().getStudentName());

            dto.setEnrollmentDate(enrollment.getEnrollmentDate());
            dto.setEnrollmentStatus(enrollment.getEnrollmentStatus());


            dto.setCourseId(enrollment.getCourse().getCourseId());
            dto.setCourseName(enrollment.getCourse().getCourseTitle());
            dtos.add(dto);
        }
        return dtos;
    }

    public List<EnrollmentDto> getEnrollment(){
        List<Enrollment> enrollmentList = enrollmentRepo.findAll();
        List<EnrollmentDto> dto = new ArrayList<>();

        for(Enrollment enrollment : enrollmentList){
            EnrollmentDto enrollmentDto = new EnrollmentDto();
            enrollmentDto.setEnrollmentId(enrollment.getEnrollmentId());
            enrollmentDto.setStudentId(enrollment.getStudent().getStudentId());
            enrollmentDto.setStudentName(enrollment.getStudent().getStudentName());
            enrollmentDto.setStudentEmail(enrollment.getStudent().getStudentEmail());
            enrollmentDto.setEnrollmentDate(enrollment.getEnrollmentDate());
            enrollmentDto.setEnrollmentStatus(enrollment.getEnrollmentStatus());
            enrollmentDto.setCourseCode(enrollment.getCourse().getCourseCode());
            enrollmentDto.setCourseInstructor(enrollment.getCourse().getCourseInstructor());

            enrollmentDto.setCourseId(enrollment.getCourse().getCourseId());
            enrollmentDto.setCourseName(enrollment.getCourse().getCourseTitle());
            dto.add(enrollmentDto);
        }
        return dto;
    }

    public List<EnrollmentDto> searchStudent(String studentName) {
        List<Student> studentList = studentRepo.findByStudentNameContainingIgnoreCase(studentName);
        List<EnrollmentDto> enrollDto = new ArrayList<>();

        for (Student student : studentList) {
            List<Enrollment> enrollments = enrollmentRepo.findByStudentId(student.getStudentId());
            for (Enrollment enrollment : enrollments) {
                EnrollmentDto enrollmentDto = new EnrollmentDto();
                enrollmentDto.setEnrollmentId(enrollment.getEnrollmentId());
                enrollmentDto.setStudentId(enrollment.getStudent().getStudentId());
                enrollmentDto.setStudentName(enrollment.getStudent().getStudentName());
                enrollmentDto.setStudentEmail(enrollment.getStudent().getStudentEmail());
                enrollmentDto.setEnrollmentDate(enrollment.getEnrollmentDate());
                enrollmentDto.setEnrollmentStatus(enrollment.getEnrollmentStatus());
                enrollmentDto.setCourseCode(enrollment.getCourse().getCourseCode());
                enrollmentDto.setCourseInstructor(enrollment.getCourse().getCourseInstructor());

                enrollmentDto.setCourseId(enrollment.getCourse().getCourseId());
                enrollmentDto.setCourseName(enrollment.getCourse().getCourseTitle());
                enrollDto.add(enrollmentDto);
            }
        }
        return enrollDto;
    }

}
