package com.example.Mid.Entity;

public class Course {

    private int id;
    private String code;
    private String title;
    private int credit;
    private String instructor;
    private int capacity;

    public Course() {
    }

    public Course(int id, String code, String title, int credit, String instructor, int capacity) {
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

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}