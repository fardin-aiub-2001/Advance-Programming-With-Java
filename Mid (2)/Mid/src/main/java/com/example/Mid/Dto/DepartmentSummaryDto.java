package com.example.Mid.Dto;

public class DepartmentSummaryDto {

    private String department;
    private int numberOfStudents;
    private int totalEnrollments;
    private double averageCgpa;
    private String popularCourse;

    public DepartmentSummaryDto(
            String department,
            int numberOfStudents,
            int totalEnrollments,
            double averageCgpa,
            String popularCourse) {

        this.department = department;
        this.numberOfStudents = numberOfStudents;
        this.totalEnrollments = totalEnrollments;
        this.averageCgpa = averageCgpa;
        this.popularCourse = popularCourse;
    }

    public String getDepartment() {
        return department;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public int getTotalEnrollments() {
        return totalEnrollments;
    }

    public double getAverageCgpa() {
        return averageCgpa;
    }

    public String getPopularCourse() {
        return popularCourse;
    }
}