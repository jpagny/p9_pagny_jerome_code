package com.mediscreen.app.bean;

import com.mediscreen.app.constant.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiabetesAssessmentBean {

    private LocalDateTime date;
    private int scoreTrigger;
    private RiskLevel riskLevel;

}
