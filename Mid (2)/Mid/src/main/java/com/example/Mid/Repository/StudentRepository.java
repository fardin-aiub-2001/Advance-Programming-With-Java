package com.example.Mid.Repository;

import com.example.Mid.Entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private static final List<Student> students = new ArrayList<>();

    static {

        students.add(new Student(
                1,
                "Rahim",
                "rahim@gmail.com",
                "CSE",
                2022
        ));

        students.add(new Student(
                2,
                "Karim",
                "karim@gmail.com",
                "CSE",
                2023
        ));

        students.add(new Student(
                3,
                "Sakib",
                "sakib@gmail.com",
                "EEE",
                2022
        ));

        students.add(new Student(
                4,
                "Nadia",
                "nadia@gmail.com",
                "BBA",
                2024
        ));

        students.add(new Student(
                5,
                "Tanvir",
                "tanvir@gmail.com",
                "CSE",
                2023
        ));
    }

    public List<Student> findAll() {
        return students;
    }

    public Student findById(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    public boolean existsByEmail(String email) {

        for (Student student : students) {

            if (student.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }

        return false;
    }

    public boolean existsByEmailExceptId(String email, int id) {

        for (Student student : students) {

            if (student.getId() != id &&
                    student.getEmail().equalsIgnoreCase(email)) {

                return true;
            }
        }

        return false;
    }

    public Student save(Student student) {

        students.add(student);

        return student;
    }

    public void deleteById(int id) {

        students.removeIf(student ->
                student.getId() == id
        );
    }

    public int getNextId() {

        int maxId = 0;

        for (Student student : students) {

            if (student.getId() > maxId) {
                maxId = student.getId();
            }
        }

        return maxId + 1;
    }
}