package com.mediscreen.app.service;

import com.mediscreen.app.bean.AssessmentBean;

public interface IAssessmentService {

    AssessmentBean getByPatientId(Long patientId);

}
