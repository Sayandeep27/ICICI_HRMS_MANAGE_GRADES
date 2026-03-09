package com.hrms.gradeband.service;

import com.hrms.gradeband.dto.GradeBandDTO;
import com.hrms.gradeband.dto.GradeBandSearchDTO;
import com.hrms.gradeband.entity.ChangeHistory;
import com.hrms.gradeband.entity.GradeBand;

import java.util.List;

public interface GradeBandService {

    GradeBand saveDraft(GradeBandDTO dto);

    GradeBand submit(GradeBandDTO dto);

    GradeBand approve(Long id);

    GradeBand reject(Long id, String remarks);

    GradeBand pushBack(Long id, String remarks);

    GradeBand modify(Long id, GradeBandDTO dto);

    List<GradeBand> search(GradeBandSearchDTO dto);

    List<ChangeHistory> history(Long gradeBandId);
}