package com.hrms.gradeband.entity;

import com.hrms.gradeband.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "grade_band")
public class GradeBand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gradeBandName;

    @Column(unique = true)
    private String gradeBandCode;

    private Integer minExperience;

    private Integer maxExperience;

    private String currency;

    private Double minSalary;

    private Double maxSalary;

    private LocalDate effectiveStartDate;

    private LocalDate effectiveEndDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;

    public Long getId() {
        return id;
    }

    public String getGradeBandName() {
        return gradeBandName;
    }

    public void setGradeBandName(String gradeBandName) {
        this.gradeBandName = gradeBandName;
    }

    public String getGradeBandCode() {
        return gradeBandCode;
    }

    public void setGradeBandCode(String gradeBandCode) {
        this.gradeBandCode = gradeBandCode;
    }

    public Integer getMinExperience() {
        return minExperience;
    }

    public void setMinExperience(Integer minExperience) {
        this.minExperience = minExperience;
    }

    public Integer getMaxExperience() {
        return maxExperience;
    }

    public void setMaxExperience(Integer maxExperience) {
        this.maxExperience = maxExperience;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Double minSalary) {
        this.minSalary = minSalary;
    }

    public Double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public LocalDate getEffectiveStartDate() {
        return effectiveStartDate;
    }

    public void setEffectiveStartDate(LocalDate effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public LocalDate getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public void setEffectiveEndDate(LocalDate effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}