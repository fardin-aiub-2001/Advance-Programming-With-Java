package com.example.Mid.Controller;

import com.example.Mid.Dto.CourseDto;
import com.example.Mid.Dto.CourseRequestDto;
import com.example.Mid.Service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(
            CourseService courseService) {

        this.courseService = courseService;
    }

    // POST /api/courses
    @PostMapping
    public ResponseEntity<CourseDto> createCourse(
            @RequestBody CourseRequestDto request) {

        CourseDto result =
                courseService.createCourse(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }

    // GET /api/courses
    // GET /api/courses?minCredit=3
    @GetMapping
    public ResponseEntity<List<CourseDto>> getCourses(
            @RequestParam(required = false)
            Double minCredit) {

        return ResponseEntity.ok(
                courseService.getCourses(minCredit)
        );
    }

    // GET /api/courses/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(
            @PathVariable int id) {

        return ResponseEntity.ok(
                courseService.getCourseById(id)
        );
    }

    // PUT /api/courses/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(
            @PathVariable int id,
            @RequestBody CourseRequestDto request) {

        return ResponseEntity.ok(
                courseService.updateCourse(
                        id,
                        request
                )
        );
    }

    // DELETE /api/courses/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable int id) {

        courseService.deleteCourse(id);

        return ResponseEntity.noContent().build();
    }
}