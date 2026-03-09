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

    @Enumerated(EnumType.STRING)
    private ActionType action;

    private LocalDateTime changeDate;

    private String changedBy;

    private String remarks;

    @ManyToOne
    @JoinColumn(name = "grade_band_id")
    private GradeBand gradeBand;

    public Long getId() {
        return id;
    }

    public ActionType getAction() {
        return action;
    }

    public void setAction(ActionType action) {
        this.action = action;
    }

    public LocalDateTime getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDateTime changeDate) {
        this.changeDate = changeDate;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public GradeBand getGradeBand() {
        return gradeBand;
    }

    public void setGradeBand(GradeBand gradeBand) {
        this.gradeBand = gradeBand;
    }
}