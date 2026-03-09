package com.hrms.gradeband.repository;

import com.hrms.gradeband.entity.GradeBand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GradeBandRepository extends JpaRepository<GradeBand, Long> {

    Optional<GradeBand> findByGradeBandCode(String code);

    List<GradeBand> findByGradeId(Long gradeId);

    List<GradeBand> findByGradeBandNameContaining(String name);

    List<GradeBand> findByGradeBandNameStartingWith(String name);
}