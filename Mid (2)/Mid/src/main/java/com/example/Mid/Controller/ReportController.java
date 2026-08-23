package com.example.Mid.Controller;

import com.example.Mid.Dto.CourseRosterDto;
import com.example.Mid.Dto.DepartmentSummaryDto;
import com.example.Mid.Dto.TopPerformerDto;
import com.example.Mid.Dto.TranscriptDto;
import com.example.Mid.Service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(
            ReportService reportService) {

        this.reportService = reportService;
    }

    // =========================================================
    // GET /api/report/students/{id}/transcript
    // =========================================================

    @GetMapping("/students/{id}/transcript")
    public ResponseEntity<TranscriptDto>
    getTranscript(
            @PathVariable int id) {

        return ResponseEntity.ok(
                reportService.getTranscript(id)
        );
    }

    // =========================================================
    // GET /api/report/courses/{id}/roster
    // =========================================================

    @GetMapping("/courses/{id}/roster")
    public ResponseEntity<CourseRosterDto>
    getCourseRoster(
            @PathVariable int id) {

        return ResponseEntity.ok(
                reportService.getCourseRoster(id)
        );
    }

    // =========================================================
    // GET /api/report/department/summary
    // =========================================================

    @GetMapping("/department/summary")
    public ResponseEntity<List<DepartmentSummaryDto>>
    getDepartmentSummary() {

        return ResponseEntity.ok(
                reportService.getDepartmentSummary()
        );
    }

    // =========================================================
    // GET /api/report/top-performers?limit=5
    // =========================================================

    @GetMapping("/top-performers")
    public ResponseEntity<List<TopPerformerDto>>
    getTopPerformers(
            @RequestParam(defaultValue = "5")
            int limit) {

        return ResponseEntity.ok(
                reportService.getTopPerformers(limit)
        );
    }
}