package com.example.Mid.Dto;

import java.util.List;

public class CourseRosterDto {

    private int courseId;
    private String code;
    private String title;
    private double credit;
    private String instructor;

    private int capacity;
    private int seatsFilled;
    private int seatsRemaining;

    private double averageGrade;

    private List<RosterStudentDto> students;

    public CourseRosterDto(
            int courseId,
            String code,
            String title,
            double credit,
            String instructor,
            int capacity,
            int seatsFilled,
            int seatsRemaining,
            double averageGrade,
            List<RosterStudentDto> students) {

        this.courseId = courseId;
        this.code = code;
        this.title = title;
        this.credit = credit;
        this.instructor = instructor;
        this.capacity = capacity;
        this.seatsFilled = seatsFilled;
        this.seatsRemaining = seatsRemaining;
        this.averageGrade = averageGrade;
        this.students = students;
    }

    public int getCourseId() {
        return courseId;
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

    public int getSeatsFilled() {
        return seatsFilled;
    }

    public int getSeatsRemaining() {
        return seatsRemaining;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public List<RosterStudentDto> getStudents() {
        return students;
    }
}