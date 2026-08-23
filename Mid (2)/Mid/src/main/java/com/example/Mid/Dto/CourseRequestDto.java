package com.example.Mid.Dto;

public class CourseRequestDto {

    private String code;
    private String title;
    private double credit;
    private String instructor;
    private int capacity;

    public CourseRequestDto() {
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

    public String getInstructor() {
        return instructor;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}