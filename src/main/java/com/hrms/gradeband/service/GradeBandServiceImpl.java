package com.hrms.gradeband.service;

import com.hrms.gradeband.dto.*;
import com.hrms.gradeband.entity.*;
import com.hrms.gradeband.enums.*;
import com.hrms.gradeband.repository.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GradeBandServiceImpl implements GradeBandService {

    private final GradeBandRepository repo;
    private final ChangeHistoryRepository historyRepo;
    private final GradeRepository gradeRepo;

    public GradeBandServiceImpl(
            GradeBandRepository repo,
            ChangeHistoryRepository historyRepo,
            GradeRepository gradeRepo) {
        this.repo = repo;
        this.historyRepo = historyRepo;
        this.gradeRepo = gradeRepo;
    }

    @Override
    public GradeBand saveDraft(GradeBandDTO dto) {

        validate(dto);

        if (repo.findByGradeBandCode(dto.getGradeBandCode()).isPresent()) {
            throw new RuntimeException("Grade Band Code already exists");
        }

        GradeBand band = map(dto);

        band.setVersion(1);   // ✅ ensure version always set
        band.setStatus(Status.DRAFT);

        repo.save(band);

        saveHistory(band, ActionType.SAVE_DRAFT, null);

        return band;
    }

    @Override
    public GradeBand submit(GradeBandDTO dto) {

        validate(dto);

        if (repo.findByGradeBandCode(dto.getGradeBandCode()).isPresent()) {
            throw new RuntimeException("Grade Band Code already exists");
        }

        GradeBand band = map(dto);

        band.setVersion(1);   // ✅ ensure version always set
        band.setStatus(Status.PENDING_APPROVAL);

        repo.save(band);

        saveHistory(band, ActionType.CREATE, null);

        return band;
    }

    @Override
    public GradeBand approve(Long id) {

        GradeBand band = repo.findById(id).orElseThrow();

        band.setStatus(Status.ACTIVE);

        repo.save(band);

        saveHistory(band, ActionType.APPROVE, null);

        return band;
    }

    @Override
    public GradeBand reject(Long id, String remarks) {

        GradeBand band = repo.findById(id).orElseThrow();

        band.setStatus(Status.REJECTED);

        repo.save(band);

        saveHistory(band, ActionType.REJECT, remarks);

        return band;
    }

    @Override
    public GradeBand pushBack(Long id, String remarks) {

        GradeBand band = repo.findById(id).orElseThrow();

        band.setStatus(Status.PUSH_BACK);

        repo.save(band);

        saveHistory(band, ActionType.PUSH_BACK, remarks);

        return band;
    }

    @Override
    public GradeBand modify(Long id, GradeBandDTO dto) {

        validate(dto);

        GradeBand existing = repo.findById(id).orElseThrow();

        // 🔥 CLOSE OLD VERSION
        existing.setEffectiveEndDate(
                dto.getEffectiveStartDate().minusDays(1)
        );

        repo.save(existing);

        // 🔥 CREATE NEW VERSION
        GradeBand newBand = map(dto);

        // ✅ FIX: HANDLE NULL VERSION SAFELY
        Integer currentVersion = existing.getVersion();
        if (currentVersion == null) {
            currentVersion = 1;
        }

        newBand.setVersion(currentVersion + 1);
        newBand.setStatus(Status.PENDING_APPROVAL);

        repo.save(newBand);

        saveHistory(newBand, ActionType.MODIFY, null);

        return newBand;
    }

    @Override
    public Page<GradeBand> advancedSearch(GradeBandSearchDTO dto) {

        Pageable pageable = PageRequest.of(dto.getPage(), dto.getSize());

        List<GradeBand> result;

        if (dto.getNameOperation() != null) {

            switch (dto.getNameOperation()) {

                case CONTAINS ->
                        result = repo.findByGradeBandNameContaining(dto.getGradeBandName());

                case STARTS_WITH ->
                        result = repo.findByGradeBandNameStartingWith(dto.getGradeBandName());

                case EQUALS ->
                        result = repo.findByGradeBandName(dto.getGradeBandName());

                case NOT_EQUALS ->
                        result = repo.findByGradeBandNameNot(dto.getGradeBandName());

                default ->
                        result = repo.findAll();
            }

        } else if (dto.getStatus() != null) {

            result = repo.findByStatus(dto.getStatus());

        } else if (dto.getGradeId() != null) {

            result = repo.findByGradeId(dto.getGradeId());

        } else {

            result = repo.findAll();
        }

        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    public List<ChangeHistory> history(Long id) {
        return historyRepo.findByGradeBandIdOrderByChangeDateDesc(id);
    }

    private GradeBand map(GradeBandDTO dto) {

        GradeBand band = new GradeBand();

        Grade grade = gradeRepo.findById(dto.getGradeId()).orElseThrow();

        band.setGrade(grade);
        band.setGradeBandName(dto.getGradeBandName());
        band.setGradeBandCode(dto.getGradeBandCode());
        band.setMinExperience(dto.getMinExperience());
        band.setMaxExperience(dto.getMaxExperience());
        band.setMinSalary(dto.getMinSalary());
        band.setMaxSalary(dto.getMaxSalary());
        band.setEffectiveStartDate(dto.getEffectiveStartDate());
        band.setCurrencyId(dto.getCurrencyId());

        return band;
    }

    private void saveHistory(GradeBand band,
                             ActionType action,
                             String remarks) {

        ChangeHistory history = new ChangeHistory();

        history.setGradeBand(band);
        history.setAction(action);
        history.setChangeDate(LocalDateTime.now());
        history.setChangedBy("SYSTEM");
        history.setRemarks(remarks);

        historyRepo.save(history);
    }

    private void validate(GradeBandDTO dto) {

        if (dto.getMinExperience() > 60)
            throw new RuntimeException("Min experience cannot exceed 60");

        if (dto.getMaxExperience() > 60)
            throw new RuntimeException("Max experience cannot exceed 60");

        if (dto.getMaxExperience() < dto.getMinExperience())
            throw new RuntimeException("Max experience cannot be less than Min experience");

        if (dto.getMinSalary() <= 0)
            throw new RuntimeException("Min salary cannot be zero");

        if (dto.getMaxSalary() < dto.getMinSalary())
            throw new RuntimeException("Max salary cannot be less than Min salary");
    }
}