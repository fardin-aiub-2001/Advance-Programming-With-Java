package com.example.Mid.Service;

import com.example.Mid.Dto.StudentDto;
import com.example.Mid.Dto.StudentRequestDto;
import com.example.Mid.Entity.Student;
import com.example.Mid.Repository.EnrollmentRepository;
import com.example.Mid.Repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    public StudentService(
            StudentRepository studentRepository,
            EnrollmentRepository enrollmentRepository) {

        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    // CREATE
    public StudentDto createStudent(StudentRequestDto request) {

        if (studentRepository.existsByEmail(request.getEmail())) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email already exists"
            );
        }

        int id = studentRepository.getNextId();

        Student student = new Student(
                id,
                request.getName(),
                request.getEmail(),
                request.getDepartment(),
                request.getAdmissionYear()
        );

        studentRepository.save(student);

        return convertToDto(student);
    }

    // GET ALL + FILTER
    public List<StudentDto> getStudents(String department) {

        List<StudentDto> result = new ArrayList<>();

        for (Student student : studentRepository.findAll()) {

            if (department == null ||
                    student.getDepartment()
                            .equalsIgnoreCase(department)) {

                result.add(convertToDto(student));
            }
        }

        return result;
    }

    // GET BY ID
    public StudentDto getStudentById(int id) {

        Student student = studentRepository.findById(id);

        if (student == null) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Student not found"
            );
        }

        return convertToDto(student);
    }

    // UPDATE
    public StudentDto updateStudent(
            int id,
            StudentRequestDto request) {

        Student student = studentRepository.findById(id);

        if (student == null) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Student not found"
            );
        }

        if (studentRepository.existsByEmailExceptId(
                request.getEmail(), id)) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email already exists"
            );
        }

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setDepartment(request.getDepartment());
        student.setAdmissionYear(request.getAdmissionYear());

        return convertToDto(student);
    }

    // DELETE
    public void deleteStudent(int id) {

        Student student = studentRepository.findById(id);

        if (student == null) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Student not found"
            );
        }

        if (enrollmentRepository.existsByStudentId(id)) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Student has enrollment and cannot be deleted"
            );
        }

        studentRepository.deleteById(id);
    }

    // ENTITY -> DTO
    private StudentDto convertToDto(Student student) {

        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getDepartment(),
                student.getAdmissionYear()
        );
    }
}