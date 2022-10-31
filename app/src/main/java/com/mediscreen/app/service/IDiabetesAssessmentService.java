package com.mediscreen.app.service;

import com.mediscreen.app.bean.DiabetesAssessmentBean;
import com.mediscreen.app.bean.PatientBean;

import java.util.List;

public interface IDiabetesAssessmentService {

    DiabetesAssessmentBean getByPatientId(Long patientId);

    List<PatientBean> getByRiskLevel(String riskLevel);

}
