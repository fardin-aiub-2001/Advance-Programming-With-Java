package com.example.Mid.Dto;

public class EnrollmentRequestDto {

    private int studentId;
    private int courseId;
    private String semester;

    public EnrollmentRequestDto() {
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getSemester() {
        return semester;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}