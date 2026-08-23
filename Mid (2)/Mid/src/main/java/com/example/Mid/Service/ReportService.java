package com.example.Mid.Service;

import com.example.Mid.Dto.CourseRosterDto;
import com.example.Mid.Dto.DepartmentSummaryDto;
import com.example.Mid.Dto.RosterStudentDto;
import com.example.Mid.Dto.TopPerformerDto;
import com.example.Mid.Dto.TranscriptCourseDto;
import com.example.Mid.Dto.TranscriptDto;
import com.example.Mid.Entity.Course;
import com.example.Mid.Entity.Enrollment;
import com.example.Mid.Entity.Student;
import com.example.Mid.Repository.CourseRepository;
import com.example.Mid.Repository.EnrollmentRepository;
import com.example.Mid.Repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public ReportService(
            StudentRepository studentRepository,
            CourseRepository courseRepository,
            EnrollmentRepository enrollmentRepository) {

        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    // =========================================================
    // 1. STUDENT TRANSCRIPT
    // GET /api/report/students/{id}/transcript
    // =========================================================

    public TranscriptDto getTranscript(int studentId) {

        Student student =
                studentRepository.findById(studentId);

        if (student == null) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Student not found"
            );
        }

        List<Enrollment> enrollments =
                enrollmentRepository.findByStudentId(studentId);

        List<TranscriptCourseDto> courses =
                new ArrayList<>();

        double totalCreditsEarned = 0.0;
        double totalGradePoints = 0.0;

        for (Enrollment enrollment : enrollments) {

            Course course =
                    courseRepository.findById(
                            enrollment.getCourseId());

            if (course == null) {
                continue;
            }

            courses.add(new TranscriptCourseDto(
                    course.getCode(),
                    course.getTitle(),
                    course.getCredit(),
                    enrollment.getSemester(),
                    enrollment.getGrade()
            ));

            /*
             * A credit is considered earned only when
             * the enrollment has a grade.
             */
            if (enrollment.getGrade() != null) {

                totalCreditsEarned += course.getCredit();

                totalGradePoints +=
                        enrollment.getGrade()
                                * course.getCredit();
            }
        }

        double cgpa = 0.0;

        if (totalCreditsEarned > 0) {

            cgpa =
                    totalGradePoints
                            / totalCreditsEarned;
        }

        cgpa = round(cgpa);

        return new TranscriptDto(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getDepartment(),
                student.getAdmissionYear(),
                totalCreditsEarned,
                cgpa,
                courses
        );
    }

    // =========================================================
    // 2. COURSE ROSTER
    // GET /api/report/courses/{id}/roster
    // =========================================================

    public CourseRosterDto getCourseRoster(int courseId) {

        Course course =
                courseRepository.findById(courseId);

        if (course == null) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Course not found"
            );
        }

        List<Enrollment> enrollments =
                enrollmentRepository.findByCourseId(courseId);

        List<RosterStudentDto> students =
                new ArrayList<>();

        double gradeSum = 0.0;
        int gradedStudents = 0;

        for (Enrollment enrollment : enrollments) {

            Student student =
                    studentRepository.findById(
                            enrollment.getStudentId());

            if (student == null) {
                continue;
            }

            students.add(new RosterStudentDto(
                    student.getId(),
                    student.getName(),
                    student.getDepartment(),
                    enrollment.getSemester(),
                    enrollment.getGrade()
            ));

            if (enrollment.getGrade() != null) {

                gradeSum += enrollment.getGrade();
                gradedStudents++;
            }
        }

        int seatsFilled = enrollments.size();

        int seatsRemaining =
                course.getCapacity() - seatsFilled;

        double averageGrade = 0.0;

        if (gradedStudents > 0) {

            averageGrade =
                    gradeSum / gradedStudents;
        }

        averageGrade = round(averageGrade);

        return new CourseRosterDto(
                course.getId(),
                course.getCode(),
                course.getTitle(),
                course.getCredit(),
                course.getInstructor(),
                course.getCapacity(),
                seatsFilled,
                seatsRemaining,
                averageGrade,
                students
        );
    }

    // =========================================================
    // 3. DEPARTMENT SUMMARY
    // GET /api/report/department/summary
    // =========================================================

    public List<DepartmentSummaryDto>
    getDepartmentSummary() {

        List<DepartmentSummaryDto> result =
                new ArrayList<>();

        List<Student> students =
                studentRepository.findAll();

        List<Enrollment> enrollments =
                enrollmentRepository.findAll();

        /*
         * First find all unique departments.
         */
        List<String> departments =
                new ArrayList<>();

        for (Student student : students) {

            if (!containsIgnoreCase(
                    departments,
                    student.getDepartment())) {

                departments.add(
                        student.getDepartment());
            }
        }

        /*
         * Calculate statistics for each department.
         */
        for (String department : departments) {

            int studentCount = 0;

            double cgpaSum = 0.0;
            int studentsWithCgpa = 0;

            for (Student student : students) {

                if (student.getDepartment()
                        .equalsIgnoreCase(department)) {

                    studentCount++;

                    double cgpa =
                            calculateStudentCgpa(
                                    student.getId());

                    if (cgpa > 0) {

                        cgpaSum += cgpa;
                        studentsWithCgpa++;
                    }
                }
            }

            /*
             * Count department enrollments
             * and course popularity.
             */
            int totalEnrollments = 0;

            Map<Integer, Integer> courseCounts =
                    new HashMap<>();

            for (Enrollment enrollment : enrollments) {

                Student student =
                        studentRepository.findById(
                                enrollment.getStudentId());

                if (student == null) {
                    continue;
                }

                if (student.getDepartment()
                        .equalsIgnoreCase(department)) {

                    totalEnrollments++;

                    int courseId =
                            enrollment.getCourseId();

                    courseCounts.put(
                            courseId,
                            courseCounts.getOrDefault(
                                    courseId, 0
                            ) + 1
                    );
                }
            }

            double averageCgpa = 0.0;

            if (studentsWithCgpa > 0) {

                averageCgpa =
                        cgpaSum / studentsWithCgpa;
            }

            averageCgpa = round(averageCgpa);

            String popularCourse =
                    findPopularCourse(courseCounts);

            result.add(
                    new DepartmentSummaryDto(
                            department,
                            studentCount,
                            totalEnrollments,
                            averageCgpa,
                            popularCourse
                    )
            );
        }

        return result;
    }

    // =========================================================
    // 4. TOP PERFORMERS
    // GET /api/report/top-performers?limit=5
    // =========================================================

    public List<TopPerformerDto>
    getTopPerformers(int limit) {

        if (limit <= 0) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Limit must be greater than 0"
            );
        }

        List<TopPerformerDto> performers =
                new ArrayList<>();

        List<Student> students =
                studentRepository.findAll();

        for (Student student : students) {

            double totalCredits =
                    calculateStudentEarnedCredits(
                            student.getId());

            double cgpa =
                    calculateStudentCgpa(
                            student.getId());

            performers.add(
                    new TopPerformerDto(
                            0,
                            student.getId(),
                            student.getName(),
                            student.getDepartment(),
                            totalCredits,
                            cgpa
                    )
            );
        }

        /*
         * Highest CGPA first.
         */
        performers.sort(
                Comparator.comparing(
                        TopPerformerDto::getCgpa
                ).reversed()
        );

        /*
         * Apply limit.
         */
        int actualLimit =
                Math.min(limit, performers.size());

        List<TopPerformerDto> result =
                new ArrayList<>();

        for (int i = 0; i < actualLimit; i++) {

            TopPerformerDto old =
                    performers.get(i);

            result.add(
                    new TopPerformerDto(
                            i + 1,
                            old.getStudentId(),
                            old.getStudentName(),
                            old.getDepartment(),
                            old.getTotalCredits(),
                            old.getCgpa()
                    )
            );
        }

        return result;
    }

    // =========================================================
    // HELPER: CALCULATE STUDENT CGPA
    // =========================================================

    private double calculateStudentCgpa(
            int studentId) {

        List<Enrollment> enrollments =
                enrollmentRepository.findByStudentId(
                        studentId);

        double totalCredits = 0.0;
        double totalPoints = 0.0;

        for (Enrollment enrollment : enrollments) {

            if (enrollment.getGrade() == null) {
                continue;
            }

            Course course =
                    courseRepository.findById(
                            enrollment.getCourseId());

            if (course == null) {
                continue;
            }

            totalCredits += course.getCredit();

            totalPoints +=
                    enrollment.getGrade()
                            * course.getCredit();
        }

        if (totalCredits == 0) {
            return 0.0;
        }

        return round(
                totalPoints / totalCredits
        );
    }

    // =========================================================
    // HELPER: EARNED CREDITS
    // =========================================================

    private double calculateStudentEarnedCredits(
            int studentId) {

        List<Enrollment> enrollments =
                enrollmentRepository.findByStudentId(
                        studentId);

        double totalCredits = 0.0;

        for (Enrollment enrollment : enrollments) {

            if (enrollment.getGrade() == null) {
                continue;
            }

            Course course =
                    courseRepository.findById(
                            enrollment.getCourseId());

            if (course != null) {

                totalCredits += course.getCredit();
            }
        }

        return totalCredits;
    }

    // =========================================================
    // HELPER: POPULAR COURSE
    // =========================================================

    private String findPopularCourse(
            Map<Integer, Integer> courseCounts) {

        if (courseCounts.isEmpty()) {
            return null;
        }

        int popularCourseId = -1;
        int highestCount = 0;

        for (Map.Entry<Integer, Integer> entry :
                courseCounts.entrySet()) {

            if (entry.getValue() > highestCount) {

                highestCount = entry.getValue();

                popularCourseId =
                        entry.getKey();
            }
        }

        Course course =
                courseRepository.findById(
                        popularCourseId);

        if (course == null) {
            return null;
        }

        return course.getCode()
                + " - "
                + course.getTitle();
    }

    // =========================================================
    // HELPER: CASE-INSENSITIVE CONTAINS
    // =========================================================

    private boolean containsIgnoreCase(
            List<String> list,
            String value) {

        for (String item : list) {

            if (item.equalsIgnoreCase(value)) {
                return true;
            }
        }

        return false;
    }

    // =========================================================
    // HELPER: ROUND TO 2 DECIMAL PLACES
    // =========================================================

    private double round(double value) {

        return Math.round(value * 100.0) / 100.0;
    }
}