package com.shubham.Student.Course.Enrollment.System.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shubham.Student.Course.Enrollment.System.Entity.Enums.StudentGender;
import com.shubham.Student.Course.Enrollment.System.Entity.Enums.StudentStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
    private String studentName;
    private String studentEmail;
    private String phoneNum;
    private LocalDate studentDob;
    @Enumerated(EnumType.STRING)
    private StudentGender studentGender;

    @Enumerated(EnumType.STRING)
    private StudentStatus studentStatus;
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonManagedReference(value = "studentEnrollment")
    List<Enrollment> studentList = new ArrayList<>();

    public Student(int studentId, String studentName, String studentEmail,String phoneNum, LocalDate studentDob, StudentGender studentGender,StudentStatus studentStatus, List<Enrollment> list) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.phoneNum = phoneNum;
        this.studentDob = studentDob;
        this.studentGender = studentGender;
        this.studentStatus = studentStatus;
        this.studentList = list;
    }

    public Student() {
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

    public List<Enrollment> getStudentList() {
        return studentList;
    }

    public StudentStatus getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
    }

    public void setStudentList(List<Enrollment> studentList) {
        this.studentList = studentList;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
