package com.mediscreen.app.service.impliment;

import com.mediscreen.app.bean.DiabetesAssessmentBean;
import com.mediscreen.app.bean.PatientBean;
import com.mediscreen.app.proxy.DiabetesAssessmentProxy;
import com.mediscreen.app.service.IDiabetesAssessmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DiabetesAssessmentService implements IDiabetesAssessmentService {
    private final DiabetesAssessmentProxy diabetesAssessmentProxy;

    @Override
    public DiabetesAssessmentBean getByPatientId(Long patientId) {
        return diabetesAssessmentProxy.getById(patientId);
    }

    @Override
    public List<PatientBean> getByRiskLevel(String riskLevel) {
        return diabetesAssessmentProxy.getByRiskLevel(riskLevel);
    }

}
