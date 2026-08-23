package com.example.Mid.Dto;

public class StudentDto {

    private int id;
    private String name;
    private String email;
    private String department;
    private int admissionYear;

    public StudentDto(
            int id,
            String name,
            String email,
            String department,
            int admissionYear) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.admissionYear = admissionYear;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
}