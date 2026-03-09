package com.hrms.gradeband.service;

import com.hrms.gradeband.dto.GradeBandRequestDTO;
import com.hrms.gradeband.dto.GradeBandSearchDTO;
import com.hrms.gradeband.entity.ChangeHistory;
import com.hrms.gradeband.entity.GradeBand;

import java.util.List;

public interface GradeBandService {

    GradeBand create(GradeBandRequestDTO req);

    GradeBand approve(Long id);

    GradeBand reject(Long id, String remarks);

    GradeBand pushBack(Long id, String remarks);

    GradeBand modify(Long id, GradeBandRequestDTO dto);

    List<GradeBand> search(GradeBandSearchDTO dto);

    List<ChangeHistory> history(Long gradeBandId);

}