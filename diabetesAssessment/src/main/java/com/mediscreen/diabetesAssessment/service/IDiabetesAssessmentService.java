package com.mediscreen.diabetesAssessment.service;

import com.mediscreen.diabetesAssessment.dto.DiabetesAssessmentDTO;

public interface IDiabetesAssessmentService {

    DiabetesAssessmentDTO getAssessmentByPatient(Long patientId);

}
