package com.example.Mid.Dto;

public class RosterStudentDto {

    private int studentId;
    private String studentName;
    private String department;
    private String semester;
    private Double grade;

    public RosterStudentDto(
            int studentId,
            String studentName,
            String department,
            String semester,
            Double grade) {

        this.studentId = studentId;
        this.studentName = studentName;
        this.department = department;
        this.semester = semester;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getDepartment() {
        return department;
    }

    public String getSemester() {
        return semester;
    }

    public Double getGrade() {
        return grade;
    }
}