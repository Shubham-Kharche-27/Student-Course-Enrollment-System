package com.shubham.Student.Course.Enrollment.System.Controller;

import com.shubham.Student.Course.Enrollment.System.Dto.EnrollmentDto;
import com.shubham.Student.Course.Enrollment.System.Service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollment")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/get")
    public ResponseEntity<Page<EnrollmentDto>> getAllEnrollmentData(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "3")int pageSize,
            @RequestParam(defaultValue = "enrollmentId")String sortBy
    ){
        return new ResponseEntity<>(enrollmentService.getAllEnrollmentData(pageNum-1,pageSize,sortBy), HttpStatus.OK);
    }

    @GetMapping("/get/byId/{enrollmentId}")
    public ResponseEntity<EnrollmentDto> getEnrollmentById(@PathVariable int enrollmentId){
        return new ResponseEntity<>(enrollmentService.getEnrollmentDataById(enrollmentId),HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<EnrollmentDto> createEnrollment(@RequestBody EnrollmentDto enrollmentDto){
        return new ResponseEntity<>(enrollmentService.createEnrollmentData(enrollmentDto), HttpStatus.CREATED);
    }

    @PutMapping("/put/{enrollmentId}")
    public ResponseEntity<String> updateEnrollment(@PathVariable int enrollmentId,@RequestBody EnrollmentDto enrollmentDto){
        return new ResponseEntity<>(enrollmentService.updateEnrollmentData(enrollmentId,enrollmentDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{enrollmentId}")
    public ResponseEntity<String> deleteEnrollment(@PathVariable int enrollmentId){
        return new ResponseEntity<>(enrollmentService.deleteEnrollmentData(enrollmentId),HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCountOfEnrollments(){
        return new ResponseEntity<>(enrollmentService.getCountOfEnrollment(),HttpStatus.OK);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<EnrollmentDto>> getRecentEnrollments(){
        return new ResponseEntity<>(enrollmentService.getRecentEnrollmentData(),HttpStatus.OK);
    }
}
