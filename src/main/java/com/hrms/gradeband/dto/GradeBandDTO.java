package com.hrms.gradeband.dto;

import java.time.LocalDate;

public class GradeBandDTO {

    private Long gradeId;
    private Long currencyId;
    private String gradeBandName;
    private String gradeBandCode;
    private Integer minExperience;
    private Integer maxExperience;
    private Double minSalary;
    private Double maxSalary;
    private LocalDate effectiveStartDate;

    public Long getGradeId() { return gradeId; }
    public void setGradeId(Long gradeId) { this.gradeId = gradeId; }

    public Long getCurrencyId() { return currencyId; }
    public void setCurrencyId(Long currencyId) { this.currencyId = currencyId; }

    public String getGradeBandName() { return gradeBandName; }
    public void setGradeBandName(String gradeBandName) { this.gradeBandName = gradeBandName; }

    public String getGradeBandCode() { return gradeBandCode; }
    public void setGradeBandCode(String gradeBandCode) { this.gradeBandCode = gradeBandCode; }

    public Integer getMinExperience() { return minExperience; }
    public void setMinExperience(Integer minExperience) { this.minExperience = minExperience; }

    public Integer getMaxExperience() { return maxExperience; }
    public void setMaxExperience(Integer maxExperience) { this.maxExperience = maxExperience; }

    public Double getMinSalary() { return minSalary; }
    public void setMinSalary(Double minSalary) { this.minSalary = minSalary; }

    public Double getMaxSalary() { return maxSalary; }
    public void setMaxSalary(Double maxSalary) { this.maxSalary = maxSalary; }

    public LocalDate getEffectiveStartDate() { return effectiveStartDate; }
    public void setEffectiveStartDate(LocalDate effectiveStartDate) { this.effectiveStartDate = effectiveStartDate; }
}