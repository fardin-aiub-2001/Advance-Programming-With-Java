package com.example.Mid.Dto;

public class TopPerformerDto {

    private int rank;
    private int studentId;
    private String studentName;
    private String department;
    private double totalCredits;
    private double cgpa;

    public TopPerformerDto(
            int rank,
            int studentId,
            String studentName,
            String department,
            double totalCredits,
            double cgpa) {

        this.rank = rank;
        this.studentId = studentId;
        this.studentName = studentName;
        this.department = department;
        this.totalCredits = totalCredits;
        this.cgpa = cgpa;
    }

    public int getRank() {
        return rank;
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

    public double getTotalCredits() {
        return totalCredits;
    }

    public double getCgpa() {
        return cgpa;
    }
}