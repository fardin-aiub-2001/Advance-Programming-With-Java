package com.example.Mid.Dto;

public class EnrollmentDto {

    private int id;
    private int studentId;
    private int courseId;
    private String semester;
    private Double grade;

    public EnrollmentDto(
            int id,
            int studentId,
            int courseId,
            String semester,
            Double grade) {

        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.semester = semester;
        this.grade = grade;
    }

    public int getId() {
        return id;
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

    public Double getGrade() {
        return grade;
    }
}