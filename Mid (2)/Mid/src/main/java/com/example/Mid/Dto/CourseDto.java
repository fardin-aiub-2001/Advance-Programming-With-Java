package com.example.Mid.Dto;

public class CourseDto {

    private int id;
    private String code;
    private String title;
    private double credit;
    private String instructor;
    private int capacity;

    public CourseDto(
            int id,
            String code,
            String title,
            double credit,
            String instructor,
            int capacity) {

        this.id = id;
        this.code = code;
        this.title = title;
        this.credit = credit;
        this.instructor = instructor;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
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
}