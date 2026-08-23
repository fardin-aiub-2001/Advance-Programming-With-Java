package com.example.Mid.Dto;

public class StudentRequestDto {

    private String name;
    private String email;
    private String department;
    private int admissionYear;

    public StudentRequestDto() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setAdmissionYear(int admissionYear) {
        this.admissionYear = admissionYear;
    }
}