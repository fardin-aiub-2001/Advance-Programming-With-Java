package com.example.Mid.Repository;

import com.example.Mid.Entity.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EnrollmentRepository {

    private static final List<Enrollment> enrollments =
            new ArrayList<>();

    static {

        enrollments.add(new Enrollment(
                1, 1, 101, "Spring 2025", 3.75
        ));

        enrollments.add(new Enrollment(
                2, 1, 102, "Spring 2025", 3.50
        ));

        enrollments.add(new Enrollment(
                3, 2, 101, "Spring 2025", 3.25
        ));

        enrollments.add(new Enrollment(
                4, 2, 103, "Fall 2025", 3.80
        ));

        enrollments.add(new Enrollment(
                5, 3, 104, "Fall 2025", 3.00
        ));

        enrollments.add(new Enrollment(
                6, 4, 102, "Spring 2026", null
        ));

        enrollments.add(new Enrollment(
                7, 4, 103, "Spring 2026", 3.60
        ));

        enrollments.add(new Enrollment(
                8, 5, 101, "Spring 2026", null
        ));
    }

    public List<Enrollment> findAll() {
        return enrollments;
    }

    public Enrollment findById(int id) {

        for (Enrollment enrollment : enrollments) {

            if (enrollment.getId() == id) {
                return enrollment;
            }
        }

        return null;
    }

    public List<Enrollment> findByStudentId(int studentId) {

        List<Enrollment> result = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {

            if (enrollment.getStudentId() == studentId) {
                result.add(enrollment);
            }
        }

        return result;
    }

    public List<Enrollment> findByCourseId(int courseId) {

        List<Enrollment> result = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {

            if (enrollment.getCourseId() == courseId) {
                result.add(enrollment);
            }
        }

        return result;
    }

    public boolean existsByStudentId(int studentId) {

        for (Enrollment enrollment : enrollments) {

            if (enrollment.getStudentId() == studentId) {
                return true;
            }
        }

        return false;
    }

    public boolean existsByCourseId(int courseId) {

        for (Enrollment enrollment : enrollments) {

            if (enrollment.getCourseId() == courseId) {
                return true;
            }
        }

        return false;
    }

    public boolean existsDuplicateEnrollment(
            int studentId,
            int courseId,
            String semester) {

        for (Enrollment enrollment : enrollments) {

            if (enrollment.getStudentId() == studentId
                    && enrollment.getCourseId() == courseId
                    && enrollment.getSemester()
                    .equalsIgnoreCase(semester)) {

                return true;
            }
        }

        return false;
    }

    public Enrollment save(Enrollment enrollment) {

        enrollments.add(enrollment);

        return enrollment;
    }

    public int getNextId() {

        int maxId = 0;

        for (Enrollment enrollment : enrollments) {

            if (enrollment.getId() > maxId) {
                maxId = enrollment.getId();
            }
        }

        return maxId + 1;
    }
}