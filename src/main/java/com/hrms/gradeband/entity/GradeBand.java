package com.hrms.gradeband.entity;

import com.hrms.gradeband.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "grade_band",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "grade_band_code")
        }
)
public class GradeBand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    Linked Grade
     */
    @ManyToOne
    @JoinColumn(name = "grade_id", nullable = false)
    private Grade grade;

    /*
    Grade Band Name
     */
    @Column(name = "grade_band_name", nullable = false)
    private String gradeBandName;

    /*
    Grade Band Code (unique)
     */
    @Column(name = "grade_band_code", nullable = false, unique = true, length = 30)
    private String gradeBandCode;

    /*
    Experience
     */
    @Column(name = "min_experience")
    private Integer minExperience;

    @Column(name = "max_experience")
    private Integer maxExperience;

    /*
    Salary
     */
    @Column(name = "min_salary")
    private Double minSalary;

    @Column(name = "max_salary")
    private Double maxSalary;

    /*
    Currency (from currency master)
     */
    @Column(name = "currency_id")
    private Long currencyId;

    /*
    Effective Dates
     */
    @Column(name = "effective_start_date")
    private LocalDate effectiveStartDate;

    @Column(name = "effective_end_date")
    private LocalDate effectiveEndDate;


    // ADD THIS FIELD INSIDE YOUR EXISTING ENTITY

    @Column(name = "version")
    private Integer version;

    public Integer getVersion() { return version; }
    public void setVersion(Integer version) { this.version = version; }

    /*
    Status for Maker-Checker
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    /*
    GETTERS AND SETTERS
     */

    public Long getId() {
        return id;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
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

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
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
}