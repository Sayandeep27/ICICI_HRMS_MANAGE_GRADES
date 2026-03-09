package com.hrms.gradeband.entity;

import com.hrms.gradeband.enums.ActionType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "change_history")
public class ChangeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long gradeBandId;

    @Enumerated(EnumType.STRING)
    private ActionType action;

    private String changedBy;

    private LocalDateTime changeDate;

    private String remarks;

    public Long getId() {
        return id;
    }

    public Long getGradeBandId() {
        return gradeBandId;
    }

    public void setGradeBandId(Long gradeBandId) {
        this.gradeBandId = gradeBandId;
    }

    public ActionType getAction() {
        return action;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public LocalDateTime getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDateTime changeDate) {
        this.changeDate = changeDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}