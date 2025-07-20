package com.shubham.Student.Course.Enrollment.System.Service;

import com.shubham.Student.Course.Enrollment.System.Dto.StudentDto;
import com.shubham.Student.Course.Enrollment.System.Entity.Student;
import com.shubham.Student.Course.Enrollment.System.Exception.StudentNotFoundException;
import com.shubham.Student.Course.Enrollment.System.Repository.StudentRepo;
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
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<Page<StudentDto>> getAllStudentData(int pageNum,int pageSize,String sortBy){
        Pageable pageable = PageRequest.of(pageNum,pageSize, Sort.by(sortBy));
        Page<Student> studentPage = studentRepo.findAll(pageable);
        Page<StudentDto> studentDtoPage = studentPage.map(Student->modelMapper.map(Student,StudentDto.class));
        return new ResponseEntity<>(studentDtoPage, HttpStatus.OK);
    }

    public ResponseEntity<StudentDto> getStudentDataById(int studentId){
        Student student = studentRepo.findById(studentId).orElseThrow(()->new StudentNotFoundException("Student not found!"));
        return new ResponseEntity<>(modelMapper.map(student,StudentDto.class),HttpStatus.OK);
    }

    public ResponseEntity<String> createStudentData(StudentDto studentDto){
        if(studentDto!=null){
            Student student = modelMapper.map(studentDto,Student.class);
            studentRepo.save(student);
            return new ResponseEntity<>("Student data created successfully!",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Student data not created!",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateStudentData(int studentId,StudentDto studentDto){
        Student student = studentRepo.findById(studentId).orElseThrow(()->new StudentNotFoundException("Student not found"));
        if(studentDto.getStudentName()!=null){
            student.setStudentName(studentDto.getStudentName());
        }
        if(studentDto.getStudentEmail()!=null){
            student.setStudentEmail(studentDto.getStudentEmail());
        }
        if(studentDto.getStudentDob()!=null){
            student.setStudentDob(studentDto.getStudentDob());
        }
        if(studentDto.getStudentGender()!=null){
            student.setStudentGender(studentDto.getStudentGender());
        }
        studentRepo.save(student);
        return new ResponseEntity<>("Student data updated successfully!",HttpStatus.OK);
    }

    public ResponseEntity<String> deleteStudentData(int studentId){
        Student student = studentRepo.findById(studentId).orElseThrow(()->new StudentNotFoundException("Student Not Found"));
        studentRepo.deleteById(studentId);
        return new ResponseEntity<>("Student data deleted successfully!",HttpStatus.NO_CONTENT);
    }
}
