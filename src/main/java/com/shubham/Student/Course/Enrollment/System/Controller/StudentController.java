package com.shubham.Student.Course.Enrollment.System.Controller;

import com.shubham.Student.Course.Enrollment.System.Dto.StudentDto;
import com.shubham.Student.Course.Enrollment.System.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity<Page<StudentDto>> getAllStudents(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "3")int pageSize,
            @RequestParam(defaultValue = "studentId")String sortBy
    ){
        return studentService.getAllStudentData(pageNum-1,pageSize,sortBy);
    }

    @GetMapping("/get/byId/{studentId}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable int studentId){
        return studentService.getStudentDataById(studentId);
    }

    @PostMapping("/post")
    public ResponseEntity<String> createStudent(@RequestBody StudentDto studentDto){
        return studentService.createStudentData(studentDto);
    }

    @PutMapping("/put/{studentId}")
    public ResponseEntity<String> updateStudent(@PathVariable int studentId,@RequestBody StudentDto studentDto){
        return studentService.updateStudentData(studentId,studentDto);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable int studentId){
        return studentService.deleteStudentData(studentId);
    }
}
