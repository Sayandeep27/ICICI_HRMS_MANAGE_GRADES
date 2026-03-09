package com.hrms.gradeband.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class GradeBandResponseDTO {

    private Long id;

    private String gradeBandName;

    private String gradeBandCode;

    private Integer minExperience;

    private Integer maxExperience;

    private String currency;

    private Double minSalary;

    private Double maxSalary;

    private LocalDate effectiveStartDate;

    private String status;

}