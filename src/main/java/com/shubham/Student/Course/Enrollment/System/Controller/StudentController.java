package com.shubham.Student.Course.Enrollment.System.Controller;

import com.shubham.Student.Course.Enrollment.System.Dto.StudentDto;
import com.shubham.Student.Course.Enrollment.System.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity<Page<StudentDto>> getAllStudents(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "3")int pageSize,
            @RequestParam(defaultValue = "studentId")String sortBy
    ){
        return new ResponseEntity<>(studentService.getAllStudentData(pageNum-1,pageSize,sortBy), HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        return new ResponseEntity<>(studentService.getAllStudentData(),HttpStatus.OK);
    }

    @GetMapping("/get/byId/{studentId}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable int studentId){
        return new ResponseEntity<>(studentService.getStudentDataById(studentId),HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto){
        return new ResponseEntity<>( studentService.createStudentData(studentDto),HttpStatus.CREATED);
    }

    @PutMapping("/put/{studentId}")
    public ResponseEntity<String> updateStudent(@PathVariable int studentId,@RequestBody StudentDto studentDto){
        return new ResponseEntity<>(studentService.updateStudentData(studentId,studentDto),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable int studentId){
        return new ResponseEntity<>(studentService.deleteStudentData(studentId),HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCountOfStudents(){
        return new ResponseEntity<>(studentService.getCountOfStudents(),HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentDto>> getSearch(@RequestParam(required = false) String name){
        return new ResponseEntity<>(studentService.searchByName(name),HttpStatus.OK);
    }
}
