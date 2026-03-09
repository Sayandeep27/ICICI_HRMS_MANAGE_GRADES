package com.hrms.gradeband.service;

import com.hrms.gradeband.dto.GradeBandRequestDTO;
import com.hrms.gradeband.dto.GradeBandSearchDTO;
import com.hrms.gradeband.entity.ChangeHistory;
import com.hrms.gradeband.entity.Grade;
import com.hrms.gradeband.entity.GradeBand;
import com.hrms.gradeband.enums.ActionType;
import com.hrms.gradeband.enums.Status;
import com.hrms.gradeband.repository.ChangeHistoryRepository;
import com.hrms.gradeband.repository.GradeBandRepository;
import com.hrms.gradeband.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public GradeBand create(GradeBandRequestDTO req) {

        if(repo.existsByGradeBandCode(req.getGradeBandCode()))
            throw new RuntimeException("Duplicate GradeBand Code");

        Grade grade = gradeRepo.findById(req.getGradeId())
                .orElseThrow(() -> new RuntimeException("Grade not found"));

        GradeBand band = new GradeBand();

        band.setGradeBandName(req.getGradeBandName());
        band.setGradeBandCode(req.getGradeBandCode());
        band.setMinExperience(req.getMinExperience());
        band.setMaxExperience(req.getMaxExperience());
        band.setCurrency(req.getCurrency());
        band.setMinSalary(req.getMinSalary());
        band.setMaxSalary(req.getMaxSalary());
        band.setEffectiveStartDate(req.getEffectiveStartDate());

        band.setStatus(Status.PENDING_APPROVAL);
        band.setGrade(grade);

        repo.save(band);

        ChangeHistory history = new ChangeHistory();
        history.setGradeBandId(band.getId());
        history.setAction(ActionType.CREATE);
        history.setChangeDate(LocalDateTime.now());

        historyRepo.save(history);

        return band;
    }

    @Override
    public GradeBand approve(Long id) {

        GradeBand band = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        band.setStatus(Status.ACTIVE);
        repo.save(band);

        ChangeHistory history = new ChangeHistory();
        history.setGradeBandId(id);
        history.setAction(ActionType.APPROVE);
        history.setChangeDate(LocalDateTime.now());

        historyRepo.save(history);

        return band;
    }

    @Override
    public GradeBand reject(Long id, String remarks) {

        GradeBand band = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        band.setStatus(Status.REJECTED);
        repo.save(band);

        ChangeHistory history = new ChangeHistory();
        history.setGradeBandId(id);
        history.setAction(ActionType.REJECT);
        history.setRemarks(remarks);
        history.setChangeDate(LocalDateTime.now());

        historyRepo.save(history);

        return band;
    }

    @Override
    public GradeBand pushBack(Long id, String remarks) {

        GradeBand band = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        band.setStatus(Status.PUSH_BACK);
        repo.save(band);

        ChangeHistory history = new ChangeHistory();
        history.setGradeBandId(id);
        history.setAction(ActionType.PUSH_BACK);
        history.setRemarks(remarks);
        history.setChangeDate(LocalDateTime.now());

        historyRepo.save(history);

        return band;
    }

    @Override
    public GradeBand modify(Long id, GradeBandRequestDTO dto) {

        GradeBand existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("GradeBand not found"));

        existing.setEffectiveEndDate(LocalDate.now());
        repo.save(existing);

        GradeBand newBand = new GradeBand();

        newBand.setGradeBandName(dto.getGradeBandName());
        newBand.setGradeBandCode(dto.getGradeBandCode());
        newBand.setMinExperience(dto.getMinExperience());
        newBand.setMaxExperience(dto.getMaxExperience());
        newBand.setCurrency(dto.getCurrency());
        newBand.setMinSalary(dto.getMinSalary());
        newBand.setMaxSalary(dto.getMaxSalary());
        newBand.setEffectiveStartDate(dto.getEffectiveStartDate());

        newBand.setStatus(Status.PENDING_APPROVAL);

        Grade grade = gradeRepo.findById(dto.getGradeId()).orElseThrow();
        newBand.setGrade(grade);

        repo.save(newBand);

        ChangeHistory history = new ChangeHistory();
        history.setGradeBandId(newBand.getId());
        history.setAction(ActionType.MODIFY);
        history.setChangeDate(LocalDateTime.now());

        historyRepo.save(history);

        return newBand;
    }

    @Override
    public List<GradeBand> search(GradeBandSearchDTO dto) {

        Status status = null;

        if(dto.getStatus()!=null)
            status = Status.valueOf(dto.getStatus());

        return repo.search(
                dto.getGradeId(),
                dto.getGradeBandName(),
                dto.getGradeBandCode(),
                status
        );
    }

    @Override
    public List<ChangeHistory> history(Long gradeBandId) {

        return historyRepo.findByGradeBandIdOrderByChangeDateDesc(gradeBandId);

    }

}