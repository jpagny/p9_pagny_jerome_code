package com.mediscreen.app.service;

import com.mediscreen.app.bean.AssessmentBean;
import com.mediscreen.app.bean.PatientBean;

import java.util.List;

public interface IDiabetesAssessmentService {

    AssessmentBean getByPatientId(Long patientId);

    List<PatientBean> getByRiskLevel(String riskLevel);

}
