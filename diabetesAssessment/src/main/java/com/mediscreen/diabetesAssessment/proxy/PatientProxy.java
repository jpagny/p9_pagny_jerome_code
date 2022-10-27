package com.mediscreen.diabetesAssessment.proxy;

import com.mediscreen.diabetesAssessment.bean.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "patient-api")
public interface PatientProxy {
    @GetMapping( value = "/patient/{id}")
    PatientBean get(@PathVariable("id") long id);

}
