package com.example.Mid.Service;

import com.example.Mid.Dto.CourseDto;
import com.example.Mid.Dto.CourseRequestDto;
import com.example.Mid.Entity.Course;
import com.example.Mid.Repository.CourseRepository;
import com.example.Mid.Repository.EnrollmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public CourseService(
            CourseRepository courseRepository,
            EnrollmentRepository enrollmentRepository) {

        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    // CREATE
    public CourseDto createCourse(CourseRequestDto request) {

        if (courseRepository.existsByCode(request.getCode())) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Course code already exists"
            );
        }

        validateCourse(request);

        int id = courseRepository.getNextId();

        Course course = new Course(
                id,
                request.getCode(),
                request.getTitle(),
                request.getCredit(),
                request.getInstructor(),
                request.getCapacity()
        );

        courseRepository.save(course);

        return convertToDto(course);
    }

    // GET ALL + MIN CREDIT FILTER
    public List<CourseDto> getCourses(Double minCredit) {

        List<CourseDto> result = new ArrayList<>();

        for (Course course : courseRepository.findAll()) {

            if (minCredit == null ||
                    course.getCredit() >= minCredit) {

                result.add(convertToDto(course));
            }
        }

        return result;
    }

    // GET BY ID
    public CourseDto getCourseById(int id) {

        Course course = courseRepository.findById(id);

        if (course == null) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Course not found"
            );
        }

        return convertToDto(course);
    }

    // UPDATE
    public CourseDto updateCourse(
            int id,
            CourseRequestDto request) {

        Course course = courseRepository.findById(id);

        if (course == null) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Course not found"
            );
        }

        if (courseRepository.existsByCodeExceptId(
                request.getCode(), id)) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Course code already exists"
            );
        }

        validateCourse(request);

        int filled =
                enrollmentRepository.findByCourseId(id).size();

        if (request.getCapacity() < filled) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Capacity cannot be less than enrolled students"
            );
        }

        course.setCode(request.getCode());
        course.setTitle(request.getTitle());
        course.setCredit(request.getCredit());
        course.setInstructor(request.getInstructor());
        course.setCapacity(request.getCapacity());

        return convertToDto(course);
    }

    // DELETE
    public void deleteCourse(int id) {

        Course course = courseRepository.findById(id);

        if (course == null) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Course not found"
            );
        }

        if (enrollmentRepository.existsByCourseId(id)) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Course has enrollment and cannot be deleted"
            );
        }

        courseRepository.deleteById(id);
    }

    private void validateCourse(CourseRequestDto request) {

        if (request.getCredit() <= 0) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Credit must be greater than 0"
            );
        }

        if (request.getCapacity() <= 0) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Capacity must be greater than 0"
            );
        }
    }

    private CourseDto convertToDto(Course course) {

        return new CourseDto(
                course.getId(),
                course.getCode(),
                course.getTitle(),
                course.getCredit(),
                course.getInstructor(),
                course.getCapacity()
        );
    }
}