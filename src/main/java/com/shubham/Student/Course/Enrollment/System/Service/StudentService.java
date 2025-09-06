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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ModelMapper modelMapper;

    public Page<StudentDto> getAllStudentData(int pageNum,int pageSize,String sortBy){
        Pageable pageable = PageRequest.of(pageNum,pageSize, Sort.by(sortBy));
        Page<Student> studentPage = studentRepo.findAll(pageable);
        return studentPage.map(Student->modelMapper.map(Student,StudentDto.class));
    }

    public List<StudentDto> getAllStudentData(){
        List<Student> studentList = studentRepo.findAll();
        List<StudentDto> dto = new ArrayList<>();
        for(Student student : studentList){
            dto.add(modelMapper.map(student, StudentDto.class));
        }
        return dto;
    }

    public StudentDto getStudentDataById(int studentId){
        Student student = studentRepo.findById(studentId).orElseThrow(()->new StudentNotFoundException("Student not found!"));
        return modelMapper.map(student,StudentDto.class);
    }

    public StudentDto createStudentData(StudentDto studentDto){
            Student student = modelMapper.map(studentDto,Student.class);
            return modelMapper.map(studentRepo.save(student), StudentDto.class);
    }

    public String updateStudentData(int studentId,StudentDto studentDto){
        Student student = studentRepo.findById(studentId).orElseThrow(()->new StudentNotFoundException("Student not found"));
        if(studentDto.getStudentName()!=null){
            student.setStudentName(studentDto.getStudentName());
        }
        if(studentDto.getStudentEmail()!=null){
            student.setStudentEmail(studentDto.getStudentEmail());
        }
        if(studentDto.getPhoneNum()!=null){
            student.setPhoneNum(studentDto.getPhoneNum());
        }
        if(studentDto.getStudentDob()!=null){
            student.setStudentDob(studentDto.getStudentDob());
        }
        if(studentDto.getStudentGender()!=null){
            student.setStudentGender(studentDto.getStudentGender());
        }
        if(studentDto.getStudentStatus()!=null){
            student.setStudentStatus(studentDto.getStudentStatus());
        }
        studentRepo.save(student);
        return "Student data updated successfully!";
    }

    public String deleteStudentData(int studentId){
        Student student = studentRepo.findById(studentId).orElseThrow(()->new StudentNotFoundException("Student Not Found"));
        studentRepo.deleteById(studentId);
        return "Student data deleted successfully!";
    }

    public Long getCountOfStudents(){
        return studentRepo.count();
    }

    public List<StudentDto> searchByName(String name){
        List<Student> studentList = studentRepo.findByStudentNameContainingIgnoreCase(name);
        List<StudentDto> dtoList = new ArrayList<>();

        for(Student student : studentList){
            StudentDto dto = modelMapper.map(student, StudentDto.class);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
