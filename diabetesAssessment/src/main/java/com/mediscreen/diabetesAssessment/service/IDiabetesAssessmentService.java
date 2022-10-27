package com.mediscreen.diabetesAssessment.service;

import com.mediscreen.diabetesAssessment.bean.PatientBean;
import com.mediscreen.diabetesAssessment.constant.RiskLevel;
import com.mediscreen.diabetesAssessment.dto.DiabetesAssessmentDTO;

import java.util.List;

public interface IDiabetesAssessmentService {

    DiabetesAssessmentDTO getAssessmentByPatient(Long patientId);
    List<PatientBean> getPatientByRiskLevel(String riskLevel);

}
