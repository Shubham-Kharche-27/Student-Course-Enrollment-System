package com.shubham.Student.Course.Enrollment.System.Controller;

import com.shubham.Student.Course.Enrollment.System.Dto.EnrollmentDto;
import com.shubham.Student.Course.Enrollment.System.Service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/get")
    public ResponseEntity<Page<EnrollmentDto>> getAllEnrollmentData(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "3")int pageSize,
            @RequestParam(defaultValue = "enrollmentId")String sortBy
    ){
        return enrollmentService.getAllEnrollmentData(pageNum-1,pageSize,sortBy);
    }

    @GetMapping("/get/byId/{enrollmentId}")
    public ResponseEntity<EnrollmentDto> getEnrollmentById(@PathVariable int enrollmentId){
        return enrollmentService.getEnrollmentDataById(enrollmentId);
    }

    @PostMapping("/post")
    public ResponseEntity<String> createEnrollment(@RequestBody EnrollmentDto enrollmentDto){
        return enrollmentService.createEnrollmentData(enrollmentDto);
    }

    @PutMapping("/put/{enrollmentId}")
    public ResponseEntity<String> updateEnrollment(@PathVariable int enrollmentId,@RequestBody EnrollmentDto enrollmentDto){
        return enrollmentService.updateEnrollmentData(enrollmentId,enrollmentDto);
    }

    @DeleteMapping("/delete/{enrollmentId}")
    public ResponseEntity<String> deleteEnrollment(@PathVariable int enrollmentId){
        return enrollmentService.deleteEnrollmentData(enrollmentId);
    }
}
