package com.hrms.gradeband.controller;

import com.hrms.gradeband.dto.GradeBandDTO;
import com.hrms.gradeband.dto.GradeBandSearchDTO;
import com.hrms.gradeband.entity.ChangeHistory;
import com.hrms.gradeband.entity.GradeBand;
import com.hrms.gradeband.service.GradeBandService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gradebands")
public class GradeBandController {

    private final GradeBandService service;

    public GradeBandController(GradeBandService service) {
        this.service = service;
    }

    @PostMapping("/draft")
    public GradeBand draft(@RequestBody GradeBandDTO dto) {
        return service.saveDraft(dto);
    }

    @PostMapping("/submit")
    public GradeBand submit(@RequestBody GradeBandDTO dto) {
        return service.submit(dto);
    }

    @PostMapping("/{id}/approve")
    public GradeBand approve(@PathVariable Long id) {
        return service.approve(id);
    }

    @PostMapping("/{id}/reject")
    public GradeBand reject(@PathVariable Long id,
                            @RequestParam String remarks) {
        return service.reject(id, remarks);
    }

    @PostMapping("/{id}/pushback")
    public GradeBand pushBack(@PathVariable Long id,
                              @RequestParam String remarks) {
        return service.pushBack(id, remarks);
    }

    @PutMapping("/{id}")
    public GradeBand modify(@PathVariable Long id,
                            @RequestBody GradeBandDTO dto) {
        return service.modify(id, dto);
    }

    @PostMapping("/search")
    public Page<GradeBand> search(@RequestBody GradeBandSearchDTO dto) {
        return service.advancedSearch(dto);
    }

    @GetMapping("/history/{id}")
    public List<ChangeHistory> history(@PathVariable Long id) {
        return service.history(id);
    }
}