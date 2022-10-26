package com.mediscreen.assessment.service;

import com.mediscreen.assessment.dto.AssessmentDTO;

public interface IAssessmentService {

    AssessmentDTO getAssessmentByPatient(Long patientId);

}
