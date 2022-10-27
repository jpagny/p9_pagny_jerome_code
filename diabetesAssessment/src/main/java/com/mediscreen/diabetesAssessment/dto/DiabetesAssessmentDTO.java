package com.mediscreen.diabetesAssessment.dto;

import com.mediscreen.diabetesAssessment.constant.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiabetesAssessmentDTO {

    private LocalDateTime date;
    private int scoreTrigger;
    private RiskLevel riskLevel;

}
