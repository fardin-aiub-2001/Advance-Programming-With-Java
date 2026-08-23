package com.example.Mid.Service;

import com.example.Mid.Dto.EnrollmentDto;
import com.example.Mid.Dto.EnrollmentRequestDto;
import com.example.Mid.Dto.GradeUpdateDto;
import com.example.Mid.Entity.Course;
import com.example.Mid.Entity.Enrollment;
import com.example.Mid.Repository.CourseRepository;
import com.example.Mid.Repository.EnrollmentRepository;
import com.example.Mid.Repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository) {

        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // CREATE ENROLLMENT
    public EnrollmentDto createEnrollment(
            EnrollmentRequestDto request) {

        // Check student
        if (studentRepository.findById(
                request.getStudentId()) == null) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Student not found"
            );
        }

        // Check course
        Course course =
                courseRepository.findById(
                        request.getCourseId());

        if (course == null) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Course not found"
            );
        }

        // Duplicate check
        if (enrollmentRepository.existsDuplicateEnrollment(
                request.getStudentId(),
                request.getCourseId(),
                request.getSemester())) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Student already enrolled in this course for this semester"
            );
        }

        // Capacity check
        int seatsFilled =
                enrollmentRepository
                        .findByCourseId(request.getCourseId())
                        .size();

        if (seatsFilled >= course.getCapacity()) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Course capacity exceeded"
            );
        }

        int id = enrollmentRepository.getNextId();

        Enrollment enrollment = new Enrollment(
                id,
                request.getStudentId(),
                request.getCourseId(),
                request.getSemester(),
                null
        );

        enrollmentRepository.save(enrollment);

        return convertToDto(enrollment);
    }

    // GET ALL
    public List<EnrollmentDto> getAllEnrollments() {

        List<EnrollmentDto> result = new ArrayList<>();

        for (Enrollment enrollment :
                enrollmentRepository.findAll()) {

            result.add(convertToDto(enrollment));
        }

        return result;
    }

    // GET BY ID
    public EnrollmentDto getEnrollmentById(int id) {

        Enrollment enrollment =
                enrollmentRepository.findById(id);

        if (enrollment == null) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Enrollment not found"
            );
        }

        return convertToDto(enrollment);
    }

    // PATCH GRADE
    public EnrollmentDto updateGrade(
            int id,
            GradeUpdateDto request) {

        Enrollment enrollment =
                enrollmentRepository.findById(id);

        if (enrollment == null) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Enrollment not found"
            );
        }

        Double grade = request.getGrade();

        if (grade == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Grade cannot be null"
            );
        }

        if (grade < 0.00 || grade > 4.00) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Grade must be between 0.00 and 4.00"
            );
        }

        enrollment.setGrade(grade);

        return convertToDto(enrollment);
    }

    private EnrollmentDto convertToDto(
            Enrollment enrollment) {

        return new EnrollmentDto(
                enrollment.getId(),
                enrollment.getStudentId(),
                enrollment.getCourseId(),
                enrollment.getSemester(),
                enrollment.getGrade()
        );
    }
}