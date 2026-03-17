package com.hrms.gradeband.repository;

import com.hrms.gradeband.entity.GradeBand;
import com.hrms.gradeband.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GradeBandRepository extends JpaRepository<GradeBand, Long> {

    // 🔹 Unique check
    Optional<GradeBand> findByGradeBandCode(String code);

    // 🔹 Search filters
    List<GradeBand> findByGradeId(Long gradeId);

    List<GradeBand> findByGradeBandNameContaining(String name);

    List<GradeBand> findByGradeBandNameStartingWith(String name);

    List<GradeBand> findByGradeBandName(String name);

    List<GradeBand> findByGradeBandNameNot(String name);

    List<GradeBand> findByStatus(Status status);

    // 🔥 IMPORTANT (NEW - FOR VERSIONING FIX)
    GradeBand findTopByGradeIdAndEffectiveEndDateIsNull(Long gradeId);
}