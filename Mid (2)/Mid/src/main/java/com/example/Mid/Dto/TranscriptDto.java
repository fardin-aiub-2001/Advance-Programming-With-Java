package com.example.Mid.Dto;

import java.util.List;

public class TranscriptDto {

    private int studentId;
    private String studentName;
    private String email;
    private String department;
    private int admissionYear;

    private double totalCreditsEarned;
    private double cgpa;

    private List<TranscriptCourseDto> courses;

    public TranscriptDto(
            int studentId,
            String studentName,
            String email,
            String department,
            int admissionYear,
            double totalCreditsEarned,
            double cgpa,
            List<TranscriptCourseDto> courses) {

        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
        this.department = department;
        this.admissionYear = admissionYear;
        this.totalCreditsEarned = totalCreditsEarned;
        this.cgpa = cgpa;
        this.courses = courses;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public int getAdmissionYear() {
        return admissionYear;
    }

    public double getTotalCreditsEarned() {
        return totalCreditsEarned;
    }

    public double getCgpa() {
        return cgpa;
    }

    public List<TranscriptCourseDto> getCourses() {
        return courses;
    }
}