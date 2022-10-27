package com.mediscreen.app.service.impliment;

import com.mediscreen.app.bean.AssessmentBean;
import com.mediscreen.app.proxy.DiabetesAssessmentProxy;
import com.mediscreen.app.service.IAssessmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AssessmentService implements IAssessmentService {
    private final DiabetesAssessmentProxy diabetesAssessmentProxy;

    public AssessmentBean getByPatientId(Long patientId) {
        return diabetesAssessmentProxy.getById(patientId);
    }

}
