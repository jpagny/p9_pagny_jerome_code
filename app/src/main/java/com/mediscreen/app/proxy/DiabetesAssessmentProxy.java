package com.mediscreen.app.proxy;

import com.mediscreen.app.bean.AssessmentBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "diabetes.assessment-api")
public interface DiabetesAssessmentProxy {

    @GetMapping("/assess/{patientId}")
    AssessmentBean getById(@PathVariable Long patientId);

}
