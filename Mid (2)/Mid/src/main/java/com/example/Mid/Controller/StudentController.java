package com.example.Mid.Controller;

import com.example.Mid.Dto.StudentDto;
import com.example.Mid.Dto.StudentRequestDto;
import com.example.Mid.Service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(
            StudentService studentService) {

        this.studentService = studentService;
    }

    // POST /api/students
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(
            @RequestBody StudentRequestDto request) {

        StudentDto result =
                studentService.createStudent(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }

    // GET /api/students
    // GET /api/students?department=CSE
    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents(
            @RequestParam(required = false)
            String department) {

        return ResponseEntity.ok(
                studentService.getStudents(department)
        );
    }

    // GET /api/students/{id}
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(
            @PathVariable int id) {

        return ResponseEntity.ok(
                studentService.getStudentById(id)
        );
    }

    // PUT /api/students/{id}
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(
            @PathVariable int id,
            @RequestBody StudentRequestDto request) {

        return ResponseEntity.ok(
                studentService.updateStudent(
                        id,
                        request
                )
        );
    }

    // DELETE /api/students/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable int id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }
}