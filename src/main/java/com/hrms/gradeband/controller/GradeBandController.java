package com.hrms.gradeband.controller;

import com.hrms.gradeband.dto.GradeBandDTO;
import com.hrms.gradeband.dto.GradeBandSearchDTO;
import com.hrms.gradeband.entity.ChangeHistory;
import com.hrms.gradeband.entity.GradeBand;
import com.hrms.gradeband.response.ApiResponse;
import com.hrms.gradeband.service.GradeBandService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v4/gradebands")
public class GradeBandController {

    private final GradeBandService service;

    public GradeBandController(GradeBandService service) {
        this.service = service;
    }

    @PostMapping("/draft")
    public ResponseEntity<ApiResponse> draft(@RequestBody GradeBandDTO dto) {

        GradeBand band = service.saveDraft(dto);

        return ResponseEntity.status(201).body(
                new ApiResponse(201, true, "Grade Band draft created successfully", band)
        );
    }

    @PostMapping("/submit")
    public ResponseEntity<ApiResponse> submit(@RequestBody GradeBandDTO dto) {

        GradeBand band = service.submit(dto);

        return ResponseEntity.status(201).body(
                new ApiResponse(201, true, "Grade Band submitted successfully", band)
        );
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<ApiResponse> approve(@PathVariable Long id) {

        GradeBand band = service.approve(id);

        return ResponseEntity.ok(
                new ApiResponse(200, true, "Grade Band approved successfully", band)
        );
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<ApiResponse> reject(@PathVariable Long id,
                                              @RequestParam String remarks) {

        GradeBand band = service.reject(id, remarks);

        return ResponseEntity.ok(
                new ApiResponse(200, true, "Grade Band rejected successfully", band)
        );
    }

    @PostMapping("/{id}/pushback")
    public ResponseEntity<ApiResponse> pushBack(@PathVariable Long id,
                                               @RequestParam String remarks) {

        GradeBand band = service.pushBack(id, remarks);

        return ResponseEntity.ok(
                new ApiResponse(200, true, "Grade Band pushed back successfully", band)
        );
    }

    @PostMapping("/{id}/modify")
    public ResponseEntity<ApiResponse> modify(@PathVariable Long id,
                                             @RequestBody GradeBandDTO dto) {

        GradeBand band = service.modify(id, dto);

        return ResponseEntity.ok(
                new ApiResponse(200, true, "Grade Band modified successfully", band)
        );
    }

    @PostMapping("/search")
    public ResponseEntity<ApiResponse> search(@RequestBody GradeBandSearchDTO dto) {

        Page<GradeBand> result = service.advancedSearch(dto);

        return ResponseEntity.ok(
                new ApiResponse(200, true, "Search completed successfully", result)
        );
    }

    @PostMapping("/history")
    public ResponseEntity<ApiResponse> history(@RequestParam Long id) {

        List<ChangeHistory> history = service.history(id);

        return ResponseEntity.ok(
                new ApiResponse(200, true, "History fetched successfully", history)
        );
    }
}