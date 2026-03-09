package com.hrms.gradeband.dto;

import com.hrms.gradeband.enums.Status;

public class GradeBandSearchDTO {

    private Long gradeId;

    private String gradeBandName;

    private String gradeBandCode;

    private SearchOperation nameOperation;

    private SearchOperation codeOperation;

    private Status status;

    private int page = 0;

    private int size = 10;

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

    public SearchOperation getNameOperation() {
        return nameOperation;
    }

    public void setNameOperation(SearchOperation nameOperation) {
        this.nameOperation = nameOperation;
    }

    public SearchOperation getCodeOperation() {
        return codeOperation;
    }

    public void setCodeOperation(SearchOperation codeOperation) {
        this.codeOperation = codeOperation;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}