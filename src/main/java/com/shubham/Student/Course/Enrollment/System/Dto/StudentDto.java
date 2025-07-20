package com.shubham.Student.Course.Enrollment.System.Dto;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDto {

    private int studentId;
    private String studentName;
    private String studentEmail;
    private LocalDate studentDob;
    private String studentGender;
    List<EnrollmentDto> list = new ArrayList<>();

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

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public List<EnrollmentDto> getList() {
        return list;
    }

    public void setList(List<EnrollmentDto> list) {
        this.list = list;
    }
}
