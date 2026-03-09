package com.hrms.gradeband.controller;

import com.hrms.gradeband.dto.GradeBandRequestDTO;
import com.hrms.gradeband.dto.GradeBandSearchDTO;
import com.hrms.gradeband.entity.ChangeHistory;
import com.hrms.gradeband.entity.GradeBand;
import com.hrms.gradeband.service.GradeBandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gradebands")
public class GradeBandController {

    private final GradeBandService service;

    public GradeBandController(GradeBandService service) {
        this.service = service;
    }

    @PostMapping
    public GradeBand create(@RequestBody GradeBandRequestDTO req){
        return service.create(req);
    }

    @PostMapping("/search")
    public List<GradeBand> search(@RequestBody GradeBandSearchDTO dto){
        return service.search(dto);
    }

    @PutMapping("/{id}")
    public GradeBand modify(@PathVariable Long id,
                            @RequestBody GradeBandRequestDTO dto){
        return service.modify(id,dto);
    }

    @PostMapping("/{id}/approve")
    public GradeBand approve(@PathVariable Long id){
        return service.approve(id);
    }

    @PostMapping("/{id}/reject")
    public GradeBand reject(@PathVariable Long id,
                            @RequestParam String remarks){
        return service.reject(id,remarks);
    }

    @PostMapping("/{id}/pushback")
    public GradeBand pushback(@PathVariable Long id,
                              @RequestParam String remarks){
        return service.pushBack(id,remarks);
    }

    @GetMapping("/{id}/history")
    public List<ChangeHistory> history(@PathVariable Long id){
        return service.history(id);
    }
}+