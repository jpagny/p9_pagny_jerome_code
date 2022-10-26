package com.mediscreen.assessment.dto;

import com.mediscreen.assessment.bean.PatientBean;
import com.mediscreen.assessment.constant.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentDTO {

    private LocalDateTime date;
    private PatientBean patient;
    private int scoreTrigger;
    private RiskLevel riskLevel;

}
