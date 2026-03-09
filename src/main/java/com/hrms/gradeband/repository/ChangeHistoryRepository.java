package com.hrms.gradeband.repository;

import com.hrms.gradeband.entity.ChangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChangeHistoryRepository
        extends JpaRepository<ChangeHistory, Long> {

    List<ChangeHistory> findByGradeBandIdOrderByChangeDateDesc(Long id);

}