package com.mediscreen.app.proxy;

import com.mediscreen.app.bean.AssessmentBean;
import com.mediscreen.app.bean.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "diabetes.assessment-api")
public interface DiabetesAssessmentProxy {

    @GetMapping("/assess/{patientId}")
    AssessmentBean getById(@PathVariable Long patientId);

    @GetMapping("/assess/list/{riskLevel}")
    List<PatientBean> getByRiskLevel(@PathVariable String riskLevel);

}
