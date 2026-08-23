package com.example.Mid.Controller;

import com.example.Mid.Dto.EnrollmentDto;
import com.example.Mid.Dto.EnrollmentRequestDto;
import com.example.Mid.Dto.GradeUpdateDto;
import com.example.Mid.Service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(
            EnrollmentService enrollmentService) {

        this.enrollmentService = enrollmentService;
    }

    // POST /api/enrollments
    @PostMapping
    public ResponseEntity<EnrollmentDto> createEnrollment(
            @RequestBody EnrollmentRequestDto request) {

        EnrollmentDto result =
                enrollmentService.createEnrollment(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }

    // GET /api/enrollments
    @GetMapping
    public ResponseEntity<List<EnrollmentDto>>
    getAllEnrollments() {

        return ResponseEntity.ok(
                enrollmentService.getAllEnrollments()
        );
    }

    // GET /api/enrollments/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDto>
    getEnrollmentById(
            @PathVariable int id) {

        return ResponseEntity.ok(
                enrollmentService.getEnrollmentById(id)
        );
    }

    // PATCH /api/enrollments/{id}/grade
    @PatchMapping("/{id}/grade")
    public ResponseEntity<EnrollmentDto> updateGrade(
            @PathVariable int id,
            @RequestBody GradeUpdateDto request) {

        return ResponseEntity.ok(
                enrollmentService.updateGrade(
                        id,
                        request
                )
        );
    }
}