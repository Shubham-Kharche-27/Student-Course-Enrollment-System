package com.shubham.Student.Course.Enrollment.System.Service;

import com.shubham.Student.Course.Enrollment.System.Dto.EnrollmentDto;
import com.shubham.Student.Course.Enrollment.System.Entity.Enrollment;
import com.shubham.Student.Course.Enrollment.System.Exception.EnrollmentNotFoundException;
import com.shubham.Student.Course.Enrollment.System.Repository.EnrollmentRepo;
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
public class EnrollmentService {

    @Autowired
    private EnrollmentRepo enrollmentRepo;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<Page<EnrollmentDto>> getAllEnrollmentData(int pageNum,int pageSize,String sortBy){
        Pageable pageable = PageRequest.of(pageNum,pageSize, Sort.by(sortBy));
        Page<Enrollment> enrollmentPage = enrollmentRepo.findAll(pageable);
        Page<EnrollmentDto> enrollmentDtoPage = enrollmentPage.map(Enrollment->modelMapper.map(Enrollment,EnrollmentDto.class));
        return new ResponseEntity<>(enrollmentDtoPage, HttpStatus.OK);
    }

    public ResponseEntity<EnrollmentDto> getEnrollmentDataById(int enrollmentId){
        Enrollment enrollment = enrollmentRepo.findById(enrollmentId).orElseThrow(()->new EnrollmentNotFoundException("Enrollment not found!"));
        return new ResponseEntity<>(modelMapper.map(enrollment,EnrollmentDto.class),HttpStatus.OK);
    }

    public ResponseEntity<String> createEnrollmentData(EnrollmentDto enrollmentDto){
        if(enrollmentDto!=null){
            Enrollment enrollment = modelMapper.map(enrollmentDto,Enrollment.class);
            enrollmentRepo.save(enrollment);
            return new ResponseEntity<>("Enrollment data created successfully!",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Enrollment data not created!",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateEnrollmentData(int enrollmentId,EnrollmentDto enrollmentDto){
        Enrollment enrollment = enrollmentRepo.findById(enrollmentId).orElseThrow(()->new EnrollmentNotFoundException("Enrollment not found!"));
        if(enrollmentDto.getStudent()!=null){
            enrollment.setStudent(enrollmentDto.getStudent());
        }
        if(enrollmentDto.getCourse()!=null){
            enrollment.setCourse(enrollmentDto.getCourse());
        }
        if(enrollmentDto.getEnrollmentDate()!=null){
            enrollment.setEnrollmentDate(enrollmentDto.getEnrollmentDate());
        }
        if(enrollmentDto.getEnrollmentStatus()!=null){
            enrollment.setEnrollmentStatus(enrollmentDto.getEnrollmentStatus());
        }
        if(enrollmentDto.getEnrollmentGrade()!=null){
            enrollment.setEnrollmentGrade(enrollmentDto.getEnrollmentGrade());
        }
        enrollmentRepo.save(enrollment);
        return new ResponseEntity<>("Enrollment data updated successfully!",HttpStatus.OK);
    }

    public ResponseEntity<String> deleteEnrollmentData(int enrollmentId){
        Enrollment enrollment = enrollmentRepo.findById(enrollmentId).orElseThrow(()->new EnrollmentNotFoundException("Enrollment not found!"));
        enrollmentRepo.deleteById(enrollmentId);
        return new ResponseEntity<>("Enrollment data deleted successfully!",HttpStatus.NO_CONTENT);
    }
}
