package com.hrms.gradeband.repository;

import com.hrms.gradeband.entity.GradeBand;
import com.hrms.gradeband.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeBandRepository extends JpaRepository<GradeBand, Long> {

    boolean existsByGradeBandCode(String code);

    @Query("""
            SELECT g FROM GradeBand g
            WHERE (:gradeId IS NULL OR g.grade.id = :gradeId)
            AND (:name IS NULL OR LOWER(g.gradeBandName) LIKE LOWER(CONCAT('%',:name,'%')))
            AND (:code IS NULL OR LOWER(g.gradeBandCode) LIKE LOWER(CONCAT('%',:code,'%')))
            AND (:status IS NULL OR g.status = :status)
            """)
    List<GradeBand> search(
            @Param("gradeId") Long gradeId,
            @Param("name") String name,
            @Param("code") String code,
            @Param("status") Status status
    );

}