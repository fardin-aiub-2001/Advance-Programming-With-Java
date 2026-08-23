package com.example.Mid.Repository;

import com.example.Mid.Entity.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {

    private static final List<Course> courses = new ArrayList<>();

    static {

        courses.add(new Course(
                101,
                "CSE101",
                "Java Programming",
                3,
                "Mr. Hasan",
                40
        ));

        courses.add(new Course(
                102,
                "CSE202",
                "Database Management",
                3,
                "Mr. Rahman",
                35
        ));

        courses.add(new Course(
                103,
                "CSE203",
                "Data Structures",
                3,
                "Mr. Karim",
                30
        ));

        courses.add(new Course(
                104,
                "CSE204",
                "Web Development",
                3,
                "Mr. Ahmed",
                40
        ));
    }

    public List<Course> findAll() {
        return courses;
    }

    public Course findById(int id) {

        for (Course course : courses) {

            if (course.getId() == id) {
                return course;
            }
        }

        return null;
    }

    public boolean existsByCode(String code) {

        for (Course course : courses) {

            if (course.getCode().equalsIgnoreCase(code)) {
                return true;
            }
        }

        return false;
    }

    public boolean existsByCodeExceptId(String code, int id) {

        for (Course course : courses) {

            if (course.getId() != id &&
                    course.getCode().equalsIgnoreCase(code)) {

                return true;
            }
        }

        return false;
    }

    public Course save(Course course) {

        courses.add(course);

        return course;
    }

    public void deleteById(int id) {

        courses.removeIf(course ->
                course.getId() == id
        );
    }

    public int getNextId() {

        int maxId = 100;

        for (Course course : courses) {

            if (course.getId() > maxId) {
                maxId = course.getId();
            }
        }

        return maxId + 1;
    }
}