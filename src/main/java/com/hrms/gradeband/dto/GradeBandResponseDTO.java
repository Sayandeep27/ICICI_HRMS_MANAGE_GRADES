package com.hrms.gradeband.dto;

public class GradeBandResponseDTO {

    private Long id;

    private String gradeBandName;

    private String gradeBandCode;

    private String status;

    private Double minSalary;

    private Double maxSalary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

}