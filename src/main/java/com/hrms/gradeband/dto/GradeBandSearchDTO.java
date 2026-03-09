package com.hrms.gradeband.dto;

public class GradeBandSearchDTO {

    private Long gradeId;
    private String gradeBandName;
    private String gradeBandCode;
    private String status;

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
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
}