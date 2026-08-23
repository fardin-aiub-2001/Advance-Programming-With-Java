package com.example.Mid.Dto;

public class TranscriptCourseDto {

    private String code;
    private String title;
    private double credit;
    private String semester;
    private Double grade;

    public TranscriptCourseDto(
            String code,
            String title,
            double credit,
            String semester,
            Double grade) {

        this.code = code;
        this.title = title;
        this.credit = credit;
        this.semester = semester;
        this.grade = grade;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public double getCredit() {
        return credit;
    }

    public String getSemester() {
        return semester;
    }

    public Double getGrade() {
        return grade;
    }
}