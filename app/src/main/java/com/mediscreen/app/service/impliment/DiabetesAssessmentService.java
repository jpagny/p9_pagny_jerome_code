package com.mediscreen.app.service.impliment;

import com.mediscreen.app.bean.DiabetesAssessmentBean;
import com.mediscreen.app.bean.PatientBean;
import com.mediscreen.app.proxy.DiabetesAssessmentProxy;
import com.mediscreen.app.service.IDiabetesAssessmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DiabetesAssessmentService implements IDiabetesAssessmentService {
    private final DiabetesAssessmentProxy diabetesAssessmentProxy;

    @Override
    public DiabetesAssessmentBean getByPatientId(Long patientId) {
        log.debug("Call diabetesAssessmentProxy.getById(patientId)");
        return diabetesAssessmentProxy.getById(patientId);
    }

    @Override
    public List<PatientBean> getByRiskLevel(String riskLevel) {
        log.debug("Call diabetesAssessmentProxy.getByRiskLevel(riskLevel)");
        return diabetesAssessmentProxy.getByRiskLevel(riskLevel);
    }

}
