package com.shubham.Student.Course.Enrollment.System.Dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.shubham.Student.Course.Enrollment.System.Entity.Enums.StudentGender;
import com.shubham.Student.Course.Enrollment.System.Entity.Enums.StudentStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDto {

    private int studentId;
    private String studentName;
    private String studentEmail;
    private String phoneNum;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate studentDob;
    private StudentGender studentGender;
    private StudentStatus studentStatus;
    List<EnrollmentDto> list = new ArrayList<>();

    public StudentStatus getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public LocalDate getStudentDob() {
        return studentDob;
    }

    public void setStudentDob(LocalDate studentDob) {
        this.studentDob = studentDob;
    }

    public StudentGender getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(StudentGender studentGender) {
        this.studentGender = studentGender;
    }

    public List<EnrollmentDto> getList() {
        return list;
    }

    public void setList(List<EnrollmentDto> list) {
        this.list = list;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
